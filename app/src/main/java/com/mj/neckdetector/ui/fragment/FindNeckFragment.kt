package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentFindNeckBinding

class FindNeckFragment : Fragment() {

    private var _binding: FragmentFindNeckBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFindNeckBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backIV.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.exerciseOne.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.findNeckCL, PointFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.exerciseTwo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.findNeckCL, PointFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.exerciseThree.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.findNeckCL, PointFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}