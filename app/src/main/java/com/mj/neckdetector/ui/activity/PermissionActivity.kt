package com.mj.neckdetector.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
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
            Manifest.permission.USE_BIOMETRIC, Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.VIBRATE
        )

        val permission = mutableMapOf<String, String>()
        permission["CAMERA"] = Manifest.permission.CAMERA
        permission["READ_EXTERNAL_STORAGE"] = Manifest.permission.READ_EXTERNAL_STORAGE
        permission["USE_BIOMETRIC"] = Manifest.permission.USE_BIOMETRIC
        permission["POST_NOTIFICATIONS"] = Manifest.permission.POST_NOTIFICATIONS
        permission["VIBRATE"] = Manifest.permission.VIBRATE

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
}