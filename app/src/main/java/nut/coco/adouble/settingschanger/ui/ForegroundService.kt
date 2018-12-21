package nut.coco.adouble.settingschanger.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.arch.lifecycle.LifecycleService
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */
class ForegroundService: LifecycleService() {

    companion object {
        var isStarted = false
    }

    override fun onCreate() {
        super.onCreate()

        isStarted = true

        Log.d("Gavnadrom", "service started")

        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                ""
            }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("This is an app")
            .setContentText("App is running")
            .setSubText("Click to open the app")
            .build()

        startForeground(124, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val channel = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_DEFAULT)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        channel.description = "Bulbul bulbul"
        channel.enableLights(true)
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }
}