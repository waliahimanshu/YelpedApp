package com.example.yelpedapp.feature.details.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HoursDTO(
    @Json(name = "open") val open: List<OpenDTO>,
    @Json(name = "hours_type") val hours_type: String,
    @Json(name = "is_open_now") val is_open_now: Boolean
)