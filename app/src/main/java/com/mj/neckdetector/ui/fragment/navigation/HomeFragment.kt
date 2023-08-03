package com.mj.neckdetector.ui.fragment.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentHomeBinding
import com.mj.neckdetector.ui.activity.MainActivity
import com.mj.neckdetector.ui.activity.MeasureActivity
import com.mj.neckdetector.ui.fragment.FindNeckFragment
import com.mj.neckdetector.utils.SharedPreferencesManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = result.data?.data
            if (selectedImageUri != null) {
                val intent = Intent(requireContext(), MeasureActivity::class.java)
                intent.putExtra("galleryUri", selectedImageUri)
                startActivity(intent)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myNickName = SharedPreferencesManager.getNickname()

        binding.nameTV.text = "${myNickName}님,"

        binding.cameraLL.setOnClickListener {
            (activity as MainActivity).firstCameraFragment()
        }

        binding.galleryLL.setOnClickListener {
            openGallery()
        }

        binding.huntLL.setOnClickListener {
            Toast.makeText(context, "꾸북이들 사냥을 갈 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.watchLL.setOnClickListener {
            Toast.makeText(context, "꾸북이들이 얼마나 많은지 감시할 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.getNeckLL.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainCL, FindNeckFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}