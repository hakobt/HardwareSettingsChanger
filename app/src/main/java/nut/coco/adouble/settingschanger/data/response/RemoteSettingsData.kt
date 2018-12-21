package nut.coco.adouble.settingschanger.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteSettingsData(

    @Json(name = "wifi")
    val wifi: Wifi,

    @Json(name = "bluetooth")
    val bluetooth: Bluetooth,

    @Json(name = "screen")
    val screen: Screen,

    @Json(name = "audio")
    val audio: Audio
)