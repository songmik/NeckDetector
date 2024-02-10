package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentPointBinding
import com.mj.neckdetector.viewmodel.fragment.PointViewModel

class PointFragment : BaseFragment<FragmentPointBinding, PointViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPointBinding {
        return FragmentPointBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): PointViewModel {
        return ViewModelProvider(this)[PointViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            Toast.makeText(context, "10원이 적립되었습니다.", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}