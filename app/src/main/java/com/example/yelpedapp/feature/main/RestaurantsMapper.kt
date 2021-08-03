package com.example.yelpedapp.feature.main

import com.example.yelpedapp.api.BusinessDTO
import com.example.yelpedapp.api.CategoryDTO
import com.example.yelpedapp.database.RestaurantsEntity
import com.example.yelpedapp.feature.main.domain.Restaurant
import javax.inject.Inject

class RestaurantsMapper @Inject constructor() {

    fun toDomain(list: List<RestaurantsEntity>): List<Restaurant> {
        return list.map {
            with(it) {
                Restaurant(
                    id = id,
                    name = name,
                    alias = alias,
                    phone = phone,
                    url = url,
                    imageUrl = imageUrl,
                    distance = distance,
                    rating = rating,
                    price = price,
                    address = address,
                    reviewCount = reviewCount.toString(),
                    category = category
                )
            }
        }
    }

    fun toDBEntity(business: List<BusinessDTO>): List<RestaurantsEntity> {
        return business.map {
            with(it) {
                RestaurantsEntity(
                    id = id,
                    name = name,
                    alias = alias,
                    phone = phone,
                    url = url,
                    imageUrl = imageURL,
                    distance = metersToMiles(distance),
                    rating = rating,
                    price = price,
                    address = santizeAddress(),
                    reviewCount = reviewCount,
                    category = flat(categories)
                )
            }
        }
    }

    private fun BusinessDTO.santizeAddress(): String {
        return  StringBuilder()
            .apply {
                with(location){
                    appendLine(address1)
                    address2?:appendLine(address2)
                    address3?:appendLine(address3)
                    appendLine(state)
                    appendLine(city)
                    appendLine(zipCode)
                }
            }.toString()
    }
}

private fun metersToMiles(totalDistanceInMeters: Float): String {
    val milesInMeter = 0.000621371
    return String.format("%.2f", totalDistanceInMeters * milesInMeter)
}

private fun flat(categories: List<CategoryDTO>): String {
    return categories.joinToString(", ") {
        it.title
    }
}