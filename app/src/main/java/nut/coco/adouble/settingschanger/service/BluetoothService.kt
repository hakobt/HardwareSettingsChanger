package nut.coco.adouble.settingschanger.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.bluetooth.BluetoothAdapter
import nut.coco.adouble.settingschanger.data.response.Bluetooth

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class BluetoothService : HardwareSettingsService<Bluetooth> {

    private val mutableBluetoothStateLiveData = MutableLiveData<Boolean>()
    val bluetoothStateLiveData: LiveData<Boolean> = mutableBluetoothStateLiveData

    override fun setSettings(data: Bluetooth) {
        if (data.enabled) {
            startBluetooth()
        } else {
            stopBluetooth()
        }
    }

    private fun isBluetoothEnabled() : Boolean {
        return BluetoothAdapter.getDefaultAdapter().isEnabled
    }

    private fun startBluetooth() {
        if (isBluetoothEnabled()) {
            return
        }
        BluetoothAdapter.getDefaultAdapter().enable()
        mutableBluetoothStateLiveData.value = true
    }

    private fun stopBluetooth() {
        if (!isBluetoothEnabled()) {
            return
        }
        BluetoothAdapter.getDefaultAdapter().disable()
        mutableBluetoothStateLiveData.value = false
    }
}