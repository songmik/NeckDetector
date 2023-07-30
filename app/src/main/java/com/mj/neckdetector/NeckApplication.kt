package com.mj.neckdetector

import android.app.Application
import com.mj.neckdetector.utils.SharedPreference

class NeckApplication: Application() {

    private lateinit var sharedPreference: SharedPreference

    fun getSharedPreference() = sharedPreference

    override fun onCreate() {
        super.onCreate()

        instance = this
        sharedPreference = SharedPreference(this)
    }

    companion object {
        private lateinit var instance: NeckApplication

        fun getInstance() = instance
    }
}