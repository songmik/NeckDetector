package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentCareBinding
import com.mj.neckdetector.utils.SharedPreferencesManager
import com.mj.neckdetector.viewmodel.fragment.CareViewModel
import java.util.Calendar

class CareFragment : BaseFragment<FragmentCareBinding, CareViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCareBinding {
        return FragmentCareBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): CareViewModel {
        return ViewModelProvider(this)[CareViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calender.minDate = Calendar.getInstance().timeInMillis


        // 닉네임 보여주기
        val myNickName = SharedPreferencesManager.getNickname()

        binding.nicknameTV.text = "$myNickName 님"


        // 설정화면으로 돌아가기
        binding.backIV.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}