package nut.coco.adouble.settingschanger.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wifi(

    @Json(name = "hot-spot")
    val hotSpot: HotSpot,

    @Json(name = "enabled")
    val enabled: Boolean
)