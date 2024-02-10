package com.mj.neckdetector.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mj.neckdetector.base.BaseActivity
import com.mj.neckdetector.databinding.ActivityNicknameBinding
import com.mj.neckdetector.utils.SharedPreferencesManager
import com.mj.neckdetector.viewmodel.activity.NicknameViewModel

class NicknameActivity : BaseActivity<ActivityNicknameBinding, NicknameViewModel>() {

    override fun getViewBinding(): ActivityNicknameBinding {
        return ActivityNicknameBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): NicknameViewModel {
        return ViewModelProvider(this)[NicknameViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            val nickname = binding.nicknameET.text.toString().trim()
            if (nickname.isNotEmpty()) {
                SharedPreferencesManager.setNickname(nickname)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}