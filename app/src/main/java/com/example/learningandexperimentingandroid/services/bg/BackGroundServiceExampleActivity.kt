package com.example.learningandexperimentingandroid.services.bg

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningandexperimentingandroid.R
import com.example.learningandexperimentingandroid.databinding.ActivityBackGroundServiceExampleBinding

class BackGroundServiceExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBackGroundServiceExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBackGroundServiceExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding) {
            btnStopService.setOnClickListener {
                stopService(
                    Intent(
                        this@BackGroundServiceExampleActivity,
                        LoggerServiceBg::class.java
                    )
                )
            }
            btnStartService.setOnClickListener {
                startService(
                    Intent(
                        this@BackGroundServiceExampleActivity,
                        LoggerServiceBg::class.java
                    )
                )
            }
        }
    }
}