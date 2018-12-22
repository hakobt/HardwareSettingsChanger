package nut.coco.adouble.settingschanger.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.wifi.WifiManager
import nut.coco.adouble.settingschanger.data.response.Wifi

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class WifiService(context: Context) : HardwareSettingsService<Wifi> {

    private val wifiStateMutableLiveData = MutableLiveData<Boolean>()
    val wifiStateLiveData: LiveData<Boolean> = wifiStateMutableLiveData

    private val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    override fun setSettings(data: Wifi) {
        wifiManager.isWifiEnabled = data.enabled
        wifiStateMutableLiveData.value = data.enabled
    }
}

