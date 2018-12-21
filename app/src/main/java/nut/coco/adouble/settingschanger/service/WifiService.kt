package nut.coco.adouble.settingschanger.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import nut.coco.adouble.settingschanger.data.response.Wifi
import android.net.wifi.WifiConfiguration
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class WifiService(private val context: Context) : HardwareSettingsService<Wifi> {

    private val wifiStateMutableLiveData = MutableLiveData<Boolean>()
    val wifiStateLiveData: LiveData<Boolean> = wifiStateMutableLiveData

    private val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    override fun setSettings(data: Wifi) {
        var wifiConfiguration = WifiConfiguration()

        wifiConfiguration.SSID = data.hotSpot.name
        wifiConfiguration.preSharedKey = data.hotSpot.password
        wifiConfiguration.allowedKeyManagement.set(4)
        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            wifiManager.startLocalOnlyHotspot(object : WifiManager.LocalOnlyHotspotCallback() {
                override fun onStarted(reservation: WifiManager.LocalOnlyHotspotReservation?) {
                    super.onStarted(reservation)
                    Log.d("WifiService", reservation?.wifiConfiguration?.SSID ?: "")
                    Log.d("WifiService", reservation?.wifiConfiguration?.FQDN ?: "")
                    Log.d("WifiService", reservation?.wifiConfiguration?.BSSID ?: "")
                    Log.d("WifiService", reservation?.wifiConfiguration?.preSharedKey ?: "")
                    Log.d("WifiService", reservation?.wifiConfiguration?.providerFriendlyName ?: "")
                }
            }, Handler(Looper.getMainLooper()))
        }
    }
}

