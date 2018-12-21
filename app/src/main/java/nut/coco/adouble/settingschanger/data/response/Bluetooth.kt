package nut.coco.adouble.settingschanger.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bluetooth(

    @Json(name = "enabled")
    val enabled: Boolean
)