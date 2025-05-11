package com.example.learningandexperimentingandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}