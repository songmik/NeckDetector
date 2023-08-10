package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            replaceFragment(PointFragment())
        }

        binding.exerciseTwo.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseThree.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseFour.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseFive.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseSix.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseSeven.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseEight.setOnClickListener {
            replaceFragment(PointFragment())
        }

        binding.exerciseNine.setOnClickListener {
            replaceFragment(PointFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.findNeckCL, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}