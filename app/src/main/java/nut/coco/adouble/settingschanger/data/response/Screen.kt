package nut.coco.adouble.settingschanger.data.response

import com.squareup.moshi.Json

data class Screen(

	@Json(name="brightness")
	val brightness: Double
)