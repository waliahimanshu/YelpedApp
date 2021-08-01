package com.example.yelpedapp.feature.details.data

import com.example.yelpedapp.api.CategoryDTO
import com.example.yelpedapp.api.LocationDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusinessDetailResponse(
    @Json(name = "id") val id: String,
    @Json(name = "alias") val alias: String,
    @Json(name = "name") val name: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "is_claimed") val isClaimed: Boolean,
    @Json(name = "is_closed") val isClosed: Boolean,
    @Json(name = "url") val yelpUrl: String,
    @Json(name = "phone") val phone: Int,
    @Json(name = "display_phone") val display_phone: String,
    @Json(name = "review_count") val review_count: Int,
    @Json(name = "categories") val categories: List<CategoryDTO>,
    @Json(name = "rating") val rating: Float,
    @Json(name = "location") val location: LocationDTO,
    @Json(name = "photos") val photos: List<String>,
    @Json(name = "price") val price: String,
    @Json(name = "hours") val hours: List<HoursDTO>,
    @Json(name = "transactions") val transactions: List<String>,
    @Json(name = "special_hours") val special_hours: List<SpecialHoursDTO>
)