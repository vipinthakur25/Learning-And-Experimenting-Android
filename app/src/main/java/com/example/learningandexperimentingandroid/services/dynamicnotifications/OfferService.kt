package com.example.learningandexperimentingandroid.services.dynamicnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.learningandexperimentingandroid.MainActivity
import com.example.learningandexperimentingandroid.R

class OfferService : Service() {
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    private lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        handlerThread = HandlerThread("OfferService")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quitSafely()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startOfferForegroundService()
        handler.post {
            trackSeconds()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun trackSeconds() {
        for (i in 10 downTo 0) {
            Thread.sleep(1000)
            val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationBuilder.setContentText("$i seconds to redeem to the offer").setSilent(true)
            nm.notify(111, notificationBuilder.build())
        }
    }

    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    fun startOfferForegroundService() {
        notificationBuilder = getNotificationBuilder()
        createNotificationChannel()
        startForeground(111, notificationBuilder.build())
    }

    private fun createNotificationChannel(): NotificationChannel {
        val channel = NotificationChannel(
            "offer",
            "DynamicNotification Running",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager =
            ContextCompat.getSystemService(this, NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
        return channel
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notification = NotificationCompat.Builder(this, "offer")
            .setContentText("60% off on selected items")
            .setContentTitle("Offer you can't refuse")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)

        return notification
    }
}