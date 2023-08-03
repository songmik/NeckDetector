package com.mj.neckdetector.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.FragmentSelectBinding
import com.mj.neckdetector.ui.activity.MeasureActivity
import com.mj.neckdetector.ui.fragment.navigation.CameraFragment

class SelectFragment : Fragment() {

    private var _binding: FragmentSelectBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val galleryUri = requireArguments().getParcelable<Uri>("galleryUri")
        if (galleryUri != null) {
            binding.previewIV.setImageURI(galleryUri)
        }

        val photoUri = requireArguments().getParcelable<Uri>("photoUri")
        if (photoUri != null) {
            binding.previewIV.setImageURI(photoUri)
        }

        binding.reLL.setOnClickListener {
            binding.reIV.setBackgroundResource(R.drawable.re_green)
            binding.reTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green2))

            val cameraFragment = CameraFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.selectContainer, cameraFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.useLL.setOnClickListener {
            binding.useIV.setBackgroundResource(R.drawable.baseline_done_green)
            binding.useTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green2))

            val intent = Intent(activity, MeasureActivity::class.java)
            intent.putExtra("photoUri", photoUri)
            intent.putExtra("galleryUri", galleryUri)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}