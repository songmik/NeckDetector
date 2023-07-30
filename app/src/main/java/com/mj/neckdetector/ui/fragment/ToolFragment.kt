package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mj.neckdetector.databinding.FragmentToolBinding
import com.mj.neckdetector.ui.activity.MainActivity

class ToolFragment : Fragment() {

    private var _binding: FragmentToolBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentToolBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolCloseIV.setOnClickListener {
            (activity as MainActivity).switchToCameraFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}