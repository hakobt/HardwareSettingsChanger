package nut.coco.adouble.settingschanger.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import nut.coco.adouble.settingschanger.LOG_TAG
import nut.coco.adouble.settingschanger.R
import nut.coco.adouble.settingschanger.viewmodel.AppViewModelFactory
import nut.coco.adouble.settingschanger.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(this, ForegroundService::class.java)

        if (!ForegroundService.isStarted) {
            Log.d(LOG_TAG, "Service already running")
            startService(serviceIntent)
        }

        val viewModel = ViewModelProviders.of(this, AppViewModelFactory()).get(MainViewModel::class.java)

        viewModel.wifiStateLiveData.observe(this, Observer {
            val enabled = it ?: return@Observer
            val text = if (enabled) getString(R.string.on) else getString(R.string.off)
            wifiStateTextView.text = text
        })

        viewModel.bluetoothStateLiveData.observe(this, Observer {
            val enabled = it ?: return@Observer
            val text = if (enabled) getString(R.string.on) else getString(R.string.off)
            bluetoothStateTextView.text = text
        })

        viewModel.screenStateLiveData.observe(this, Observer {
            val brightness = it ?: return@Observer
            brightnessStateTextView.text = brightness.toString()
        })

        viewModel.volumeStateLiveData.observe(this, Observer {
            val volume = it ?: return@Observer
            volumeStateTextView.text = volume.toString()
        })

        shutDown.setOnClickListener {
            stopService(serviceIntent)
            finish()
        }
    }
}
