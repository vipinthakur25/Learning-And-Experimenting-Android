package com.example.learningandexperimentingandroid.services.fg

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.learningandexperimentingandroid.MainActivity
import com.example.learningandexperimentingandroid.R
import kotlin.concurrent.thread

class LoggerServiceFG : Service() {
    companion object {
        const val TAG = "ForegroundService"
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
        startLoggerFGService()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun startLoggerFGService() {
        createNotificationChannel()
        val notification = createNotification()
        startForeground(123, notification)

    }

    private fun createNotificationChannel(): NotificationChannel {
        val channel = NotificationChannel(
            "ID",
            "ForeGroundService Learning",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager =
            ContextCompat.getSystemService(this, NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
        return channel
    }

    private fun createNotification(): Notification {
        val notification = Notification.Builder(this, "ID")
            .setContentText("ForegroundService Running")
            .setContentTitle("Learning")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
        return notification
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

}