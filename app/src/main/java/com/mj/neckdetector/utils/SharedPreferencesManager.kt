package com.mj.neckdetector.utils

import com.mj.neckdetector.NeckApplication

object SharedPreferencesManager {

    private val preference = NeckApplication.getInstance().getSharedPreference()

    // 앱 최초 실행 여부 판단
    fun setFirstRun(isFirstRun: Boolean) {
        preference.set("isFirstRun", isFirstRun)
    }

    fun getFirstRun(): Boolean {
        return preference.get("isFirstRun", true)
    }


    // 닉네임 저장
    fun setNickname(nickname: String) {
        preference.set("nickname", nickname)
    }

    fun getNickname(): String {
        return preference.get("nickname", "")
    }
}