package nut.coco.adouble.settingschanger.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.provider.Settings
import nut.coco.adouble.settingschanger.data.response.Screen

/**
 * Created by Hakob Tovmasyan on 12/20/18
 * Package nut.coco.adouble.settingschanger
 */
class ScreenService(private val context: Context) : HardwareSettingsService<Screen> {

    private val brightnessValueMutableLiveData = MutableLiveData<Int>()
    val brightnessLiveData: LiveData<Int> = brightnessValueMutableLiveData

    override fun setSettings(data: Screen) {
        changeBrightness((data.brightness * 255).toInt())
    }

    private fun changeBrightness(brightness: Int) {
        try {
            Settings.System.putInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
            brightnessValueMutableLiveData.value = brightness
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
    }
}