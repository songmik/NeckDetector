package com.mj.neckdetector.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mj.neckdetector.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideBottomBar()
        checkPermissions()

        binding.startButton.setOnClickListener {
            val intent = Intent(this, NicknameActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermissions() {
        val arrayPermission = arrayOf(
            Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.USE_BIOMETRIC, Manifest.permission.POST_NOTIFICATIONS
        )

        val permission = mutableMapOf<String, String>()
        permission["CAMERA"] = Manifest.permission.CAMERA
        permission["READ_EXTERNAL_STORAGE"] = Manifest.permission.READ_EXTERNAL_STORAGE
        permission["USE_BIOMETRIC"] = Manifest.permission.USE_BIOMETRIC
        permission["POST_NOTIFICATIONS"] = Manifest.permission.POST_NOTIFICATIONS

        val denied = permission.count {
            ContextCompat.checkSelfPermission(
                this,
                it.value
            ) == PackageManager.PERMISSION_DENIED
        }

        if (denied > 0) {
            requestPermissions(permission.values.toTypedArray(), 100)
        } else {
            ActivityCompat.requestPermissions(this, arrayPermission, 400)
        }
    }


    private fun hideBottomBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)

            val controller = window.insetsController
            if (controller != null) {
                // navigationBars -> 하단바 제거
                controller.hide(WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

}