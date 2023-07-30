package com.mj.neckdetector.utils

import android.content.Context

class SharedPreference(context: Context) {

    private val sharedPreferences = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // 데이터 저장
    fun set(key: String, value: Any) {
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> throw UnsupportedOperationException()
        }
        editor.apply()
    }

    // 데이터 가져 오기
    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, defaultValue: T?): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            else -> throw UnsupportedOperationException()
        }
    }

    // 데이터 삭제
    fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }

    companion object {
        const val PREF_NAME = "com.mj.neckdetector"
    }
}