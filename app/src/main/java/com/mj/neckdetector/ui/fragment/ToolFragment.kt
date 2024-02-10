package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentToolBinding
import com.mj.neckdetector.ui.activity.MainActivity
import com.mj.neckdetector.viewmodel.fragment.ToolViewModel

class ToolFragment : BaseFragment<FragmentToolBinding, ToolViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentToolBinding {
        return FragmentToolBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): ToolViewModel {
        return ViewModelProvider(this)[ToolViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolCloseIV.setOnClickListener {
            (activity as MainActivity).switchToCameraFragment()
        }
    }
}