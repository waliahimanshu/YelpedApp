package com.example.yelpedapp.feature.details.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecialHoursDTO(

    @Json(name = "date") val date: String,
    @Json(name = "is_closed") val is_closed: String,
    @Json(name = "start") val start: Int,
    @Json(name = "end") val end: Int,
    @Json(name = "is_overnight") val is_overnight: Boolean
)