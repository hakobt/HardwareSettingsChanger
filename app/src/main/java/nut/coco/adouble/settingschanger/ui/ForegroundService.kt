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
import nut.coco.adouble.settingschanger.LOG_TAG
import nut.coco.adouble.settingschanger.R

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 *
 * Since android N we have to keep a ForegroundService to ensure app is not killed by Android System.
 */
class ForegroundService: LifecycleService() {

    // Keep a static variable to check if service is started to avoid creating new service
    companion object {
        var isStarted = false
    }

    override fun onCreate() {
        super.onCreate()

        isStarted = true

        Log.d(LOG_TAG, "foreground service started")

        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("HardwareSettingsChangerChannel", "Changing hardware settings")
            } else {
                ""
            }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Listening for settings changes")
            .setContentText("App is running")
            .setSubText("Click to open the app")
            .build()

        startForeground(31, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        channel.description = "Changing hardware settings"
        channel.name = "Changing hardware settings"
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }
}