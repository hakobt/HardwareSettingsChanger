package nut.coco.adouble.settingschanger.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.media.AudioManager
import nut.coco.adouble.settingschanger.data.response.Audio

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class VolumeService(context: Context) : HardwareSettingsService<Audio> {

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val volumeStateMutableLiveData = MutableLiveData<Int>()
    val volumeStateLiveData: LiveData<Int> = volumeStateMutableLiveData

    override fun setSettings(data: Audio) {
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION)
        val finalVolume = (data.volume * maxVolume).toInt()

        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, finalVolume, 0)

        volumeStateMutableLiveData.value = finalVolume
    }
}