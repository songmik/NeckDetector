package com.mj.neckdetector.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentCameraBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startCamera()
        //  초기화
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()

        binding.takeIV.setOnClickListener {
            takePhoto()
        }

        binding.photoIV.setOnClickListener {

        }

        binding.turnIV.setOnClickListener {
            // 카메라 화면 전환하는 부분
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = File(
            outputDirectory,
            SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.KOREA).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    Log.d("CameraFragment", msg)
                    // 여기서 사진을 저장한 후 필요한 작업을 수행할 수 있습니다.

                    // 미리보기 수행하는 방법
                    //binding.previewIV.setImageURI(savedUri)
                    val selectFragment = SelectFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("photoUri", savedUri)
                    selectFragment.arguments = bundle

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.cameraContainer, selectFragment)
                        .addToBackStack(null)
                        .commit()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("CameraFragment", "Photo capture failed: ${exception.message}", exception)
                }
            })
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        imageCapture = ImageCapture.Builder()
            .build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                viewLifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        } catch (exc: Exception) {
            Log.e("CameraFragment", "Use case binding failed", exc)
        }
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireContext().externalCacheDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireContext().cacheDir
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}