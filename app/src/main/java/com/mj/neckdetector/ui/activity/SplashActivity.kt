package com.mj.neckdetector.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mj.neckdetector.utils.SharedPreferencesManager

class SplashActivity : AppCompatActivity() {

    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}