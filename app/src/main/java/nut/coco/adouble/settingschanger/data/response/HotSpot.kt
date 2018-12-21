package nut.coco.adouble.settingschanger.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotSpot(

	@Json(name="password")
	val password: String,

	@Json(name="name")
	val name: String,

	@Json(name="enabled")
	val enabled: Boolean
)