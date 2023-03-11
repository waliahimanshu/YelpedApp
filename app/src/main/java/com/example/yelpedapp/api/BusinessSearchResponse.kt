package com.example.yelpedapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusinessSearchResponse(
    @Json(name = "total") val total: Long,
    @Json(name = "businesses") val business: List<BusinessDTO>,
    @Json(name = "region") val region: RegionDTO
)

data class RegionDTO(
    @Json(name = "center") val center: CenterDTO
)

data class CenterDTO(
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double
)

