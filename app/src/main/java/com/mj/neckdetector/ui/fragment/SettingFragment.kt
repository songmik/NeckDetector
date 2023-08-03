package com.mj.neckdetector.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.mj.neckdetector.NeckApplication
import com.mj.neckdetector.databinding.FragmentSettingBinding
import com.mj.neckdetector.ui.activity.SplashActivity
import com.mj.neckdetector.utils.SharedPreference

class SettingFragment : Fragment() {

    private var _binding : FragmentSettingBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutTV.setOnClickListener {
            showLogoutAlertDialog(requireContext()) {
                val sharedPreferences = SharedPreference(requireContext())
                sharedPreferences.remove("isFirstRun")
                sharedPreferences.remove("nickname")

                val intent = Intent(NeckApplication.getInstance(), SplashActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                NeckApplication.getInstance().startActivity(intent)
            }
        }

        binding.backIV.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun showLogoutAlertDialog(context: Context, onLogoutConfirmed: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("로그아웃")
        builder.setMessage("로그아웃하시겠습니까?")
        builder.setPositiveButton("확인") { _, _ ->
            onLogoutConfirmed.invoke()
        }
        builder.setNegativeButton("취소", null)
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}