package com.example.learningandexperimentingandroid.services.fg

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningandexperimentingandroid.R
import com.example.learningandexperimentingandroid.databinding.ActivityForGroundServiceExampleBinding

class ForGroundServiceExampleActivity : AppCompatActivity() {
    lateinit var binding: ActivityForGroundServiceExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForGroundServiceExampleBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                200
            )
        }


        with(binding) {
            btnStartService.setOnClickListener {
                startForegroundService(
                    Intent(
                        this@ForGroundServiceExampleActivity,
                        LoggerServiceFG::class.java
                    )
                )
            }
            btnStopService.setOnClickListener {
                stopService(
                    Intent(
                        this@ForGroundServiceExampleActivity,
                        LoggerServiceFG::class.java
                    )
                )
            }
        }
    }
}