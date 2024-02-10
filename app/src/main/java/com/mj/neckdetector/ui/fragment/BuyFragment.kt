package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentBuyBinding
import com.mj.neckdetector.viewmodel.fragment.BuyViewModel

class BuyFragment : BaseFragment<FragmentBuyBinding, BuyViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBuyBinding {
        return FragmentBuyBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): BuyViewModel {
        return ViewModelProvider(this)[BuyViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backIV.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}