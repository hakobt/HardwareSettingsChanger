package nut.coco.adouble.settingschanger.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import nut.coco.adouble.settingschanger.App
import nut.coco.adouble.settingschanger.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Gavnadrom"
    }

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(this, ForegroundService::class.java)

        if (!ForegroundService.isStarted) {
            startService(serviceIntent)
        }

        val settingsRepository = App.instance.settingsRepository

        settingsRepository.start()

        settingsRepository.wifiStateLiveData.observe(this, Observer {

        })

        settingsRepository.bluetootStateLiveData.observe(this, Observer {
            val enabled = it ?: return@Observer
            Log.d(TAG, "bluetooth $enabled")
        })

        settingsRepository.brightnessLiveData.observe(this, Observer {
            val brightness = it ?: return@Observer
            Log.d(TAG, "brightness $brightness")
        })

        settingsRepository.volumeStateLiveData.observe(this, Observer {
            val volume = it ?: return@Observer
            Log.d(TAG, "brightness $volume")
        })

        shutDown.setOnClickListener {
            stopService(serviceIntent)
            finish()
        }
    }
}
