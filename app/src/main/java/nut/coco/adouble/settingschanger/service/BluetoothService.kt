package nut.coco.adouble.settingschanger.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import nut.coco.adouble.settingschanger.data.response.Bluetooth

/**
 * Created by Hakob Tovmasyan on 12/19/18
 * Package nut.coco.adouble.settingschanger
 */

class BluetoothService(context: Context): HardwareSettingsService<Bluetooth> {

    private val mutableBluetoothStateLiveData = MutableLiveData<Boolean>()
    val bluetoothStateLiveData: LiveData<Boolean> = mutableBluetoothStateLiveData

    override fun setSettings(data: Bluetooth) {
        if (data.enabled) {
            startBluetooth()
        } else {
            stopBluetooth()
        }
    }

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    private fun startBluetooth() {
        BluetoothAdapter.getDefaultAdapter().enable()
        mutableBluetoothStateLiveData.value = true
    }

    private fun stopBluetooth() {
        BluetoothAdapter.getDefaultAdapter().disable()
        mutableBluetoothStateLiveData.value = false
    }
}