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

    private lateinit var bluetoothService: BluetoothService
    private lateinit var wifiService: WifiService
    private lateinit var volumeService: VolumeService
    private lateinit var screenService: ScreenService

    private lateinit var remoteDataSource: RemoteDataSource

    lateinit var settingsRepository: SettingsRepository

    override fun onCreate() {
        super.onCreate()

        instance = this

        bluetoothService = BluetoothService(this)
        wifiService = WifiService(this)
        volumeService = VolumeService(this)
        screenService = ScreenService(this)

        remoteDataSource = RemoteDataSource()

        settingsRepository = SettingsRepository(
            bluetoothService,
            wifiService,
            volumeService,
            screenService,
            remoteDataSource
        )
    }
}