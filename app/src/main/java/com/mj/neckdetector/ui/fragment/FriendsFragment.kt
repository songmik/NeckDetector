package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseFragment
import com.mj.neckdetector.databinding.FragmentFriendsBinding
import com.mj.neckdetector.viewmodel.fragment.FriendsViewModel

class FriendsFragment : BaseFragment<FragmentFriendsBinding, FriendsViewModel>() {

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFriendsBinding {
        return FragmentFriendsBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): FriendsViewModel {
        return ViewModelProvider(this)[FriendsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backIV.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}