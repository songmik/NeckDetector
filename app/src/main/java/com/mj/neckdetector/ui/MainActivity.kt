package com.mj.neckdetector.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mj.neckdetector.R
import com.mj.neckdetector.databinding.ActivityMainBinding
import com.mj.neckdetector.ui.fragment.CameraFragment
import com.mj.neckdetector.ui.fragment.HomeFragment
import com.mj.neckdetector.ui.fragment.MyFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val cameraFragment = CameraFragment()
    private val myFragment = MyFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideBottomBar()

        checkPermissions()

        setCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> setCurrentFragment(homeFragment)
                R.id.camaraFragment -> setCurrentFragment(cameraFragment)
                R.id.myFragment -> setCurrentFragment(myFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }

    private fun checkPermissions() {
        val arrayPermission = arrayOf(Manifest.permission.CAMERA)

        val permission = mutableMapOf<String, String>()
        permission["CAMERA"] = Manifest.permission.CAMERA

        val denied = permission.count { ContextCompat.checkSelfPermission(this, it.value) == PackageManager.PERMISSION_DENIED }

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
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}