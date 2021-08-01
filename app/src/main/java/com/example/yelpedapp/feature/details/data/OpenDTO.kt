package com.example.yelpedapp.feature.details.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenDTO (
	@Json(name = "is_overnight") val is_overnight : Boolean,
	@Json(name = "start") val start : Int,
	@Json(name = "end") val end : Int,
	@Json(name = "day") val day : Int
)