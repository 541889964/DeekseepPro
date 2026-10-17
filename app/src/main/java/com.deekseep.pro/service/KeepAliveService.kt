package com.deekseep.pro.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class KeepAliveService : Service() {
    companion object {
        const val CH = "deekseep_keepalive"
        const val ID = 3001
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val c = NotificationChannel(CH, "后台服务", NotificationManager.IMPORTANCE_LOW)
            getSystemService(NotificationManager::class.java).createNotificationChannel(c)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(ID, NotificationCompat.Builder(this, CH)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle("Deekseep Pro")
            .setContentText("后台运行中")
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build())
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
