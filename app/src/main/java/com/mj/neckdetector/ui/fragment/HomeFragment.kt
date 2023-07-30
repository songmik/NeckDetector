package com.mj.neckdetector.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mj.neckdetector.databinding.FragmentHomeBinding
import com.mj.neckdetector.utils.SharedPreferencesManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myNickName = SharedPreferencesManager.getNickname()

        binding.nameTV.text = "${myNickName}님,"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}