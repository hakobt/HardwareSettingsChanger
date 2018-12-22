package nut.coco.adouble.settingschanger

import android.app.Application
import nut.coco.adouble.settingschanger.data.SettingsRepository
import nut.coco.adouble.settingschanger.network.RemoteDataSource
import nut.coco.adouble.settingschanger.service.BluetoothService
import nut.coco.adouble.settingschanger.service.ScreenService
import nut.coco.adouble.settingschanger.service.VolumeService
import nut.coco.adouble.settingschanger.service.WifiService

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var settingsRepository: SettingsRepository

    override fun onCreate() {
        super.onCreate()

        instance = this

        val bluetoothService = BluetoothService()
        val wifiService = WifiService(this)
        val volumeService = VolumeService(this)
        val screenService = ScreenService(this)

        val remoteDataSource = RemoteDataSource()

        settingsRepository = SettingsRepository(
            bluetoothService,
            wifiService,
            volumeService,
            screenService,
            remoteDataSource
        )
    }
}