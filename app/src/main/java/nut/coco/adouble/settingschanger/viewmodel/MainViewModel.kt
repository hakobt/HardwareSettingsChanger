package nut.coco.adouble.settingschanger.viewmodel

import android.arch.lifecycle.ViewModel
import nut.coco.adouble.settingschanger.data.SettingsRepository

/**
 * Created by Hakob Tovmasyan on 12/22/18
 * Package nut.coco.adouble.settingschanger.viewmodel
 */
class MainViewModel(settingsRepository: SettingsRepository): ViewModel() {
    val bluetoothStateLiveData = settingsRepository.bluetoothStateLiveData
    val wifiStateLiveData = settingsRepository.wifiStateLiveData
    val volumeStateLiveData = settingsRepository.volumeStateLiveData
    val screenStateLiveData = settingsRepository.brightnessLiveData

    init {
        settingsRepository.start()
    }
}