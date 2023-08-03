package com.mj.neckdetector.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mj.neckdetector.databinding.FragmentPointBinding

class PointFragment : Fragment() {

    private var _binding: FragmentPointBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            Toast.makeText(context, "10원이 적립되었습니다.", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}