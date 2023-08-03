package com.mj.neckdetector.ui.fragment

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentTotalBinding
import com.mj.neckdetector.ui.activity.MainActivity
import com.mj.neckdetector.utils.SharedPreferencesManager

class TotalFragment : Fragment() {

    private var _binding: FragmentTotalBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTotalBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 단계별 텍스트를 나눠야 할 것 같음
        val highFromActivity = requireArguments().getInt("highPercentage")

        if (highFromActivity != null) {
            Log.e("결과값", "$highFromActivity")

            when (highFromActivity) {
                in 0..25 -> {
                    binding.totalTV.text = "꾸북이 사냥꾼"
                    binding.goobokIV.setBackgroundResource(R.drawable.step_one)
                    binding.levelTV.text = "Lv.4"
                    binding.nameTV.text = "꾸북이 사냥꾼"
                    binding.totalOneTV.text = "훌륭해요! 하지만 방심은 금물입니다."
                    binding.totalTwoTV.text = "거북목은 예기치 않게 발생할 수 있는 문제이기 때문에, \n계속해서 관리하고 정기적인 스트레칭과 운동을 통해"
                    binding.totalThreeTV.text = "건강한 목을 유지하세요. \n예방이 치료보다 중요하니까요!"

                }

                in 26..50 -> {
                    binding.totalTV.text = "예비 꾸북이"
                    binding.goobokIV.setBackgroundResource(R.drawable.step_two)
                    binding.levelTV.text = "Lv.3"
                    binding.nameTV.text = "예비 꾸북이"
                    binding.totalOneTV.text = "꾸북이가 될 가능성이 보여요!"
                    binding.totalTwoTV.text = "꾸북이탐지기와 함께 거북목을 예방하고 관리하면"
                    binding.totalThreeTV.text = "앞으로도 충분히 건강한 목을 가질 수 있을 거예요!"

                }

                in 51..75 -> {
                    binding.totalTV.text = "꾸북이"
                    binding.goobokIV.setBackgroundResource(R.drawable.step_three)
                    binding.levelTV.text = "Lv.2"
                    binding.nameTV.text = "꾸북이"
                    binding.totalOneTV.text = "꾸북보스가 되지 않게 조심하세요."
                    binding.totalTwoTV.text = "지금은 2단계이지만, 정기적 스트레칭과 올바른\n자세 관리로 더 나은 상태로 이끌 수 있습니다."
                    binding.totalThreeTV.text = "건강을 지키며 거북목에 대한 주의를 기울여보세요."

                }

                in 76..100 -> {
                    binding.totalTV.text = "꾸북보스"
                    binding.goobokIV.setBackgroundResource(R.drawable.step_four)
                    binding.levelTV.text = "Lv.1"
                    binding.nameTV.text = "꾸북보스"
                    binding.totalOneTV.text = "영원한 꾸북이로 남으실 건가요?"
                    binding.totalTwoTV.text = "지금은 거북목이 높게 측정 되었을 수도 있지만, \n꾸준한 운동과 치료를 통해 호전될 수 있습니다."
                    binding.totalThreeTV.text = "꾸북이탐지기가 관리할 수 있도록 도와드릴게요."

                }

                else -> {
                    Toast.makeText(context, "꾸북력 측정이 되지 않았습니다. 다시 측정해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val myNickName = SharedPreferencesManager.getNickname()
        binding.nicknameTV.text = "${myNickName}님은"

        binding.shareIV.setOnClickListener {
            binding.shareIV.visibility = View.INVISIBLE
            binding.reButton.visibility = View.INVISIBLE
            binding.stretchButton.visibility = View.INVISIBLE

            val screenshot = takeScreenshot()
            binding.shareIV.visibility = View.VISIBLE
            binding.reButton.visibility = View.VISIBLE
            binding.stretchButton.visibility = View.VISIBLE

            if (screenshot != null) {
                saveScreenshot(screenshot)
            }
        }

        binding.reButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        binding.stretchButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.totalCL, FindNeckFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    private fun takeScreenshot(): Bitmap? {
        val rootView = requireActivity().window.decorView
        val screenshot = Bitmap.createBitmap(
            rootView.width,
            rootView.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(screenshot)
        rootView.draw(canvas)

        return screenshot
    }


    private fun saveScreenshot(screenshot: Bitmap) {
        val contentResolver: ContentResolver = requireContext().contentResolver
        val imageFileName = "Screenshot_${System.currentTimeMillis()}.jpg"
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Screenshots")
            }
        }

        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri?.let {
            contentResolver.openOutputStream(it)?.use { outputStream ->
                screenshot.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            showImageSavedDialog()
        }
    }

    private fun showImageSavedDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("이미지 저장")
            .setMessage("이미지를 앨범에 저장합니다.")
            .setPositiveButton("OK") { _, _ -> }
            .create()

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}