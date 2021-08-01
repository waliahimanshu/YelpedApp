package com.example.yelpedapp.feature.main

import com.example.yelpedapp.api.BusinessDTO
import com.example.yelpedapp.api.CategoryDTO
import com.example.yelpedapp.database.RestaurantsEntity
import com.example.yelpedapp.feature.main.domain.Restaurants
import javax.inject.Inject

class RestaurantsMapper @Inject constructor() {

    fun toDomain(list: List<RestaurantsEntity>): List<Restaurants> {
        return list.map {
            with(it) {
                Restaurants(
                    id = id,
                    name = name,
                    imageUrl = imageUrl,
                    distance = distance,
                    rating = rating,
                    price = price,
                    address1 = address1,
                    reviewCount = reviewCount,
                    category = category
                )
            }
        }
    }

    fun toDBEntity(business: List<BusinessDTO>): List<RestaurantsEntity> {
        return business.map {
            with(it) {
                RestaurantsEntity(
                    id,
                    name,
                    imageURL,
                    metersToMiles(distance),
                    rating,
                    price,
                    location.address1,
                    reviewCount,
                    flat(categories)
                )
            }
        }
    }

}

private fun metersToMiles(totalDistanceInMeters: Double): String {
    val milesInMeter = 0.000621371
    return String.format("%.2f", totalDistanceInMeters * milesInMeter)
}

private fun flat(categories: List<CategoryDTO>): String {
    return categories.joinToString(", ") {
        it.title
    }
}