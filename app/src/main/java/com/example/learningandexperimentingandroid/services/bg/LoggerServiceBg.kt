package com.example.learningandexperimentingandroid.services.bg

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread


class LoggerServiceBg : Service() {
    companion object {
        const val TAG = "BackgroundService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread(start = true) {
            while (true) {
                Log.d(TAG, "Logging message")
                Thread.sleep(1000)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}