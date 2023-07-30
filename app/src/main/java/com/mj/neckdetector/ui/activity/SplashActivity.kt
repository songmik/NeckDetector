package com.mj.neckdetector.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.mj.neckdetector.databinding.ActivitySplashBinding
import com.mj.neckdetector.utils.SharedPreferencesManager

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideBottomBar()

        handler.postDelayed({
            val isFirstRun = SharedPreferencesManager.getFirstRun()

            if (isFirstRun) {
                // 최초 실행인 경우
                val intent = Intent(this, ViewPagerActivity::class.java)
                startActivity(intent)
                finish()
            } else  {
                // 이미 실행된 경우
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
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