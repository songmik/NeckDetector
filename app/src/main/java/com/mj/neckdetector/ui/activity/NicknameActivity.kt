package com.mj.neckdetector.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.mj.neckdetector.databinding.ActivityNicknameBinding
import com.mj.neckdetector.utils.SharedPreferencesManager

class NicknameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideBottomBar()

        binding.nextButton.setOnClickListener {
            val nickname = binding.nicknameET.text.toString().trim()
            if (nickname.isNotEmpty()) {
                SharedPreferencesManager.setNickname(nickname)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun hideBottomBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)

            val controller = window.insetsController
            if (controller != null) {
                // navigationBars -> 하단바 제거
                controller.hide(WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}