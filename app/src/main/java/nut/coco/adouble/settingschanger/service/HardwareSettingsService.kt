package nut.coco.adouble.settingschanger.service

interface HardwareSettingsService<T> {

    fun setSettings(data: T)

}