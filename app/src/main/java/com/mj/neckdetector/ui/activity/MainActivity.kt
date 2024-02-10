package com.mj.neckdetector.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.R
import com.mj.neckdetector.base.BaseActivity
import com.mj.neckdetector.databinding.ActivityMainBinding
import com.mj.neckdetector.ui.fragment.ToolFragment
import com.mj.neckdetector.ui.fragment.navigation.CameraFragment
import com.mj.neckdetector.ui.fragment.navigation.HomeFragment
import com.mj.neckdetector.ui.fragment.navigation.MyFragment
import com.mj.neckdetector.utils.SharedPreferencesManager
import com.mj.neckdetector.viewmodel.activity.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val homeFragment = HomeFragment()
    private val cameraFragment = CameraFragment()
    private val myFragment = MyFragment()
    private val toolFragment = ToolFragment()
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> setCurrentFragment(homeFragment)
                R.id.camaraFragment -> firstCameraFragment()
                R.id.myFragment -> setCurrentFragment(myFragment)

            }
            true
        }

    }

    // ToolFragment에서 CameraFragment로 전환하는 메서드
    fun switchToCameraFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, cameraFragment)
            .addToBackStack(null)
            .commit()
    }

    fun firstCameraFragment()   {
        val isFirstRun = SharedPreferencesManager.getFirstRun()
        if (isFirstRun) {
            // CameraFragment를 배경으로 먼저 추가합니다.
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, cameraFragment)
                .commit()

            // ToolFragment를 위에 추가합니다.
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, toolFragment)
                .addToBackStack(null)
                .commit()

            SharedPreferencesManager.setFirstRun(false)
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, CameraFragment())
                .commit()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }

}