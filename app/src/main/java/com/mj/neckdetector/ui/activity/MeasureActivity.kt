package com.mj.neckdetector.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mj.neckdetector.databinding.ActivityMeasureBinding

class MeasureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeasureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeasureBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}