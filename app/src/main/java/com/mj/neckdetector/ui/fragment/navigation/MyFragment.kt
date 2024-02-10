package com.mj.neckdetector.ui.fragment.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.R
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentMyBinding
import com.mj.neckdetector.ui.fragment.BuyFragment
import com.mj.neckdetector.ui.fragment.CareFragment
import com.mj.neckdetector.ui.fragment.FindNeckFragment
import com.mj.neckdetector.ui.fragment.OutFragment
import com.mj.neckdetector.ui.fragment.SettingFragment
import com.mj.neckdetector.utils.SharedPreferencesManager
import com.mj.neckdetector.viewmodel.fragment.navigation.MyViewModel

class MyFragment : BaseFragment<FragmentMyBinding, MyViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMyBinding {
        return FragmentMyBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MyViewModel {
        return ViewModelProvider(this)[MyViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myNickName = SharedPreferencesManager.getNickname()

        binding.nicknameTV.text = "$myNickName 님"

        binding.settingIV.setOnClickListener {
            addFragment(SettingFragment())
        }

        binding.careLL.setOnClickListener {
            addFragment(CareFragment())
        }

        binding.outLL.setOnClickListener {
            addFragment(OutFragment())
        }

        binding.findNeckLL.setOnClickListener {
            addFragment(FindNeckFragment())
        }

        binding.buyLL.setOnClickListener {
            addFragment(BuyFragment())
        }

        binding.meetingLL.setOnClickListener {
            makeToast("동료를 만나러 갔으니 잠시만 기다려주세요.")
        }

        binding.badgeLL.setOnClickListener {
            makeToast("뱃지 수집 준비 중입니다.")
        }
    }

    private fun addFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.myFL, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}