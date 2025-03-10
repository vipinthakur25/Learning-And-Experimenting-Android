package com.example.learningandexperimentingandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningandexperimentingandroid.databinding.ActivityMainBinding
import com.example.learningandexperimentingandroid.services.bg.BackGroundServiceExampleActivity
import com.example.learningandexperimentingandroid.services.dynamicnotifications.DynamicNotificationActivity
import com.example.learningandexperimentingandroid.services.fg.ForGroundServiceExampleActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding) {
            btnBgService.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, BackGroundServiceExampleActivity::class.java
                    )
                )
            }
            btnFgService.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, ForGroundServiceExampleActivity::class.java
                    )
                )
            }
            btnDynamicService.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, DynamicNotificationActivity::class.java
                    )
                )
            }
        }

    }
}