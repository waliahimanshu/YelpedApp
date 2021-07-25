package com.example.yelpedapp.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusinessSearchResponse(
    val total: Long,
    val business: List<BusinessDTO>,
    val region: RegionDTO
)

data class RegionDTO(
    val center: CenterDTO
)

data class CenterDTO(
    val latitude: Double,
    val longitude: Double
)

