package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mj.neckdetector.databinding.FragmentTotalBinding

class TotalFragment : Fragment() {

    private var _binding: FragmentTotalBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTotalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 단계별 텍스트를 나눠야 할 것 같음
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}