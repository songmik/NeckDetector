package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.R
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentFindNeckBinding
import com.mj.neckdetector.viewmodel.fragment.FindNeckViewModel

class FindNeckFragment : BaseFragment<FragmentFindNeckBinding, FindNeckViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFindNeckBinding {
        return FragmentFindNeckBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): FindNeckViewModel {
        return ViewModelProvider(this)[FindNeckViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pointFragment = PointFragment()

        binding.backIV.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.exerciseOne.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseTwo.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseThree.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseFour.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseFive.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseSix.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseSeven.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseEight.setOnClickListener {
            replaceFragment(pointFragment)
        }

        binding.exerciseNine.setOnClickListener {
            replaceFragment(pointFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.findNeckFL, fragment)
            .addToBackStack(null)
            .commit()
    }
}