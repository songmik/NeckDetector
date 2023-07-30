package com.mj.neckdetector.ui.fragment.viewpager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mj.neckdetector.databinding.FragmentViewThreeBinding
import com.mj.neckdetector.ui.activity.PermissionActivity

class ViewThreeFragment : Fragment() {

    private var _binding: FragmentViewThreeBinding ?= null
    private val binding get()= _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       _binding = FragmentViewThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            val intent = Intent(activity, PermissionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}