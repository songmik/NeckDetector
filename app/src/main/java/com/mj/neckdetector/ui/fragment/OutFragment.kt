package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentOutBinding
import com.mj.neckdetector.utils.SharedPreferencesManager
import com.mj.neckdetector.viewmodel.fragment.OutViewModel

class OutFragment : BaseFragment<FragmentOutBinding, OutViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOutBinding {
        return FragmentOutBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): OutViewModel {
        return ViewModelProvider(this)[OutViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myNickName = SharedPreferencesManager.getNickname()

        binding.nicknameTV.text = "$myNickName 님"

        binding.backIV.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}