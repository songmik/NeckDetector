package com.mj.neckdetector.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentMyBinding
import com.mj.neckdetector.utils.SharedPreferencesManager

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myNickName = SharedPreferencesManager.getNickname()

        binding.nicknameTV.text = "$myNickName 님"

        binding.settingIV.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.myContainer, SettingFragment()) // R.id.fragment_container는 Fragment가 들어갈 레이아웃 컨테이너의 ID입니다.
            transaction.addToBackStack(null) // 이전 Fragment로 돌아가기 위해 back stack에 추가합니다.
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}