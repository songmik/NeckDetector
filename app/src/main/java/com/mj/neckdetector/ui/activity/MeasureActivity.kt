package com.mj.neckdetector.ui.activity

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.mj.neckdetector.R
import com.mj.neckdetector.base.BaseActivity
import com.mj.neckdetector.databinding.ActivityMeasureBinding
import com.mj.neckdetector.ui.fragment.TotalFragment
import com.mj.neckdetector.viewmodel.activity.MeasureViewModel
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class MeasureActivity : BaseActivity<ActivityMeasureBinding, MeasureViewModel>() {

    private lateinit var tflite: Interpreter

    private val modelInputSize = 224
    override fun getViewBinding(): ActivityMeasureBinding {
        return ActivityMeasureBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MeasureViewModel {
        return ViewModelProvider(this)[MeasureViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        val galleryUriString: Uri ?= intent.getParcelableExtra("galleryUri")
        if (galleryUriString != null) {
            val galleryUri = Uri.parse(galleryUriString.toString())

            Log.e("uri Test", "$galleryUri")
            try {
                val inputStream = this.contentResolver.openInputStream(galleryUriString)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, modelInputSize, modelInputSize, false)
                val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)

                // Load and run TensorFlow Lite model
                tflite = Interpreter(loadModelFile())

                val modelOutputShape = tflite.getOutputTensor(0).shape()
                val outputArray = Array(modelOutputShape[0]) { FloatArray(modelOutputShape[1]) }

                tflite.run(byteBuffer, outputArray)

                var highestProbability = 0.0f
                var highestProbabilityClassIndex = -1
                for (i in 0 until modelOutputShape[1]) {
                    if (outputArray[0][i] > highestProbability) {
                        highestProbability = outputArray[0][i]
                        highestProbabilityClassIndex = i
                    }
                }

                if (highestProbabilityClassIndex != -1) {
                    val highPercentage = (highestProbability * 100).toInt()
                    val lowPercentage = 100-highPercentage

                    binding.percentTV.text = "${highPercentage}%"
                    val totalFragment = TotalFragment()
                    val bundle = Bundle()
                    bundle.putInt("highPercentage", highPercentage)
                    totalFragment.arguments = bundle

                    binding.nextButton.setOnClickListener {
                        replaceFragment(totalFragment)
                        binding.nextButton.visibility = View.GONE
                    }

                    Log.w("결과", "거북목 될 확률 : $highPercentage")
                    Log.w("결과", "거북목 아닐 확률 : $lowPercentage")

                    displayChart(highPercentage, lowPercentage)
                }
            } catch (e:IOException) {

            }
        }

        // 넘겨 받은 uri를 이용해서 모델에 넣는 곳 -> 널처리 안하면 에러 발생
        val imageUriString: Uri ? = bundle?.getParcelable("photoUri")
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString.toString())
            Log.e("uri Test", "$imageUri")
            try {
                val inputStream = FileInputStream(imageUri.path)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, modelInputSize, modelInputSize, false)
                val byteBuffer = convertBitmapToByteBuffer(scaledBitmap)

                // Load and run TensorFlow Lite model
                tflite = Interpreter(loadModelFile())

                val modelOutputShape = tflite.getOutputTensor(0).shape()
                val outputArray = Array(modelOutputShape[0]) { FloatArray(modelOutputShape[1]) }

                tflite.run(byteBuffer, outputArray)

                var highestProbability = 0.0f
                var highestProbabilityClassIndex = -1
                for (i in 0 until modelOutputShape[1]) {
                    if (outputArray[0][i] > highestProbability) {
                        highestProbability = outputArray[0][i]
                        highestProbabilityClassIndex = i
                    }
                }

                if (highestProbabilityClassIndex != -1) {
                    val highPercentage = (highestProbability * 100).toInt()
                    val lowPercentage = 100-highPercentage
                    binding.percentTV.text = "${highPercentage}%"

                    val totalFragment = TotalFragment()
                    val bundle = Bundle()
                    bundle.putInt("highPercentage", highPercentage)
                    totalFragment.arguments = bundle
                    binding.nextButton.setOnClickListener {
                        replaceFragment(totalFragment)
                        binding.nextButton.visibility = View.GONE
                    }
                    Log.w("결과", "거북목 될 확률 : $highPercentage")
                    Log.w("결과", "거북목 아닐 확률 : $lowPercentage")

                    displayChart(highPercentage, lowPercentage)
                }
            } catch (e:IOException) {

            }
        }
    }

    // 차트 그리는 함수
    private fun displayChart(high: Int, low: Int) {
        val pieEntries = ArrayList<PieEntry>()
        pieEntries.add(PieEntry(high.toFloat(), ""))
        pieEntries.add(PieEntry(low.toFloat(), ""))

        val colors = ArrayList<Int>()
        colors.add(ContextCompat.getColor(applicationContext, R.color.pink))
        colors.add(ContextCompat.getColor(applicationContext, R.color.grayf8))

        // PieDataSet 생성 및 데이터와 레이블 설정
        val pieDataSet = PieDataSet(pieEntries, null)
        pieDataSet.colors = colors
        pieDataSet.label = ""
        pieDataSet.setDrawValues(false)

        // PieData에 PieDataSet 설정
        val pieData = PieData(pieDataSet)

        // 차트에 서식 적용
        binding.chart.apply {
            data = pieData
            setUsePercentValues(false)
            description.isEnabled = false
            legend.isEnabled = false
            animateY(3000)
            invalidate()
        }
    }

    // 딥러닝 모델 파일을 가져와 읽는 함수
    private fun loadModelFile(): ByteBuffer {
        val assetManager: AssetManager = assets
        val modelFileDescriptor = assetManager.openFd("model.tflite")
        val inputStream = FileInputStream(modelFileDescriptor.fileDescriptor)
        val fileChannel: FileChannel = inputStream.channel
        val startOffset = modelFileDescriptor.startOffset
        val declaredLength = modelFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(modelInputSize * modelInputSize * 3 * 4)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(modelInputSize * modelInputSize)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        var pixel = 0
        for (i in 0 until modelInputSize) {
            for (j in 0 until modelInputSize) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((value shr 8 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((value and 0xFF) / 255.0f))
            }
        }
        return byteBuffer
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.measureFL, fragment)
            .addToBackStack(null)
            .commit()
    }
}