package com.example.yelpedapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusinessDTO(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "rating") val rating: Double,
    @Json(name = "price") val price: String?,
    @Json(name = "phone") val phone: String,
    @Json(name = "alias") val alias: String,
    @Json(name = "is_closed") val isClosed: Boolean,
    @Json(name = "categories") val categories: List<CategoryDTO>,
    @Json(name = "review_count") val reviewCount: Long,
    @Json(name = "url") val url: String,
    @Json(name = "coordinates") val coordinates: CenterDTO,
    @Json(name = "image_url") val imageURL: String,
    @Json(name = "location") val location: LocationDTO,
    @Json(name = "distance") val distance: Double,
    @Json(name = "transactions") val transactions: List<String>
)

data class CategoryDTO(
    @Json(name = "alias") val alias: String,
    @Json(name = "title") val title: String
)

data class LocationDTO(
   @Json(name = "city") val city: String,
   @Json(name = "country") val country: String,
   @Json(name = "address2") val address2: String?,
   @Json(name = "address3") val address3: String?,
   @Json(name = "state") val state: String,
   @Json(name = "address1") val address1: String,
   @Json(name = "zip_code") val zipCode: String
)