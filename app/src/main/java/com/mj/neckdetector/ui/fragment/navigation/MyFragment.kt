package com.mj.neckdetector.ui.fragment.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentMyBinding
import com.mj.neckdetector.ui.fragment.BuyFragment
import com.mj.neckdetector.ui.fragment.CareFragment
import com.mj.neckdetector.ui.fragment.FindNeckFragment
import com.mj.neckdetector.ui.fragment.OutFragment
import com.mj.neckdetector.ui.fragment.SettingFragment
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

        binding.careLL.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.myContainer, CareFragment()) // R.id.fragment_container는 Fragment가 들어갈 레이아웃 컨테이너의 ID입니다.
            transaction.addToBackStack(null) // 이전 Fragment로 돌아가기 위해 back stack에 추가합니다.
            transaction.commit()
        }

        binding.outLL.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.myContainer, OutFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.findNeckLL.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.myContainer, FindNeckFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.buyLL.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.myContainer, BuyFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.meetingLL.setOnClickListener {
            Toast.makeText(context, "동료를 만나러 갔으니 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }

        binding.badgeLL.setOnClickListener {
            Toast.makeText(context, "뱃지 수집 준비 중입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}