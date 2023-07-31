package com.mj.neckdetector.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentSelectBinding
import com.mj.neckdetector.ui.activity.MeasureActivity

class SelectFragment : Fragment() {

    private var _binding: FragmentSelectBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoUri = requireArguments().getParcelable<Uri>("photoUri")
        if (photoUri != null) {
            binding.previewIV.setImageURI(photoUri)
        }

        binding.reLL.setOnClickListener {
            val cameraFragment = CameraFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.selectContainer, cameraFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.useLL.setOnClickListener {
            val intent = Intent(activity, MeasureActivity::class.java)
            intent.putExtra("photoUri", photoUri)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}