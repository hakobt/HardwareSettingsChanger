package nut.coco.adouble.settingschanger.data

import nut.coco.adouble.settingschanger.data.response.RemoteSettingsData
import nut.coco.adouble.settingschanger.network.RemoteDataSource
import nut.coco.adouble.settingschanger.service.BluetoothService
import nut.coco.adouble.settingschanger.service.ScreenService
import nut.coco.adouble.settingschanger.service.VolumeService
import nut.coco.adouble.settingschanger.service.WifiService

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */
class SettingsRepository(
    private val bluetoothService: BluetoothService,
    private val wifiService: WifiService,
    private val volumeService: VolumeService,
    private val screenService: ScreenService,
    private val remoteDataSource: RemoteDataSource
) {

    val bluetootStateLiveData = bluetoothService.bluetoothStateLiveData
    val wifiStateLiveData = wifiService.wifiStateLiveData
    val volumeStateLiveData = volumeService.volumeStateLiveData
    val brightnessLiveData = screenService.brightnessLiveData

    init {
        remoteDataSource.callback = {
            changeSettings(it)
        }
    }

    fun start() {
        remoteDataSource.start()
    }

    private fun changeSettings(remoteSettingsData: RemoteSettingsData) {
        volumeService.setSettings(remoteSettingsData.audio)
        wifiService.setSettings(remoteSettingsData.wifi)
        bluetoothService.setSettings(remoteSettingsData.bluetooth)
        screenService.setSettings(remoteSettingsData.screen)
    }
}