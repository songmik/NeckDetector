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
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.R
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentHomeBinding
import com.mj.neckdetector.ui.activity.MainActivity
import com.mj.neckdetector.ui.activity.MeasureActivity
import com.mj.neckdetector.ui.fragment.BuyFragment
import com.mj.neckdetector.ui.fragment.FindNeckFragment
import com.mj.neckdetector.utils.SharedPreferencesManager
import com.mj.neckdetector.viewmodel.fragment.navigation.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var backTime: Long = 0

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

    private val onBackCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() > backTime + 2000) {
                backTime = System.currentTimeMillis()
                Toast.makeText(requireContext(), "뒤로 가기를 한 번 더 누를 시 앱이 종료 됩니다.", Toast.LENGTH_SHORT).show()
            } else {
                requireActivity().finish()
            }
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), onBackCallback)

        val myNickName = SharedPreferencesManager.getNickname()

        binding.nameTV.text = "${myNickName}님,"

        binding.cameraLL.setOnClickListener {
            (activity as MainActivity).firstCameraFragment()
        }

        binding.galleryLL.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryLauncher.launch(intent)
        }

        binding.huntLL.setOnClickListener {
            Toast.makeText(context, "꾸북이들 사냥을 갈 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.watchLL.setOnClickListener {
            Toast.makeText(context, "꾸북이들이 얼마나 많은지 감시할 예정입니다.", Toast.LENGTH_SHORT).show()
        }

        binding.getNeckLL.setOnClickListener {
            addFragment(FindNeckFragment())
        }

        binding.goToBuyLL.setOnClickListener {
            addFragment(BuyFragment())
        }

    }

    private fun addFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.homeMainFL, fragment)
            .addToBackStack(null)
            .commit()
    }

//    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        galleryLauncher.launch(intent)
//    }
}