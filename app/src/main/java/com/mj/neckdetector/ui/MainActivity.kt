package com.mj.neckdetector.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}