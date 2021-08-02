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
                    isClosed = isClosed,
                    phone = phone,
                    url = url,
                    transactions = transactions,
                    imageUrl = imageUrl,
                    distance = distance,
                    rating = rating,
                    price = price,
                    address1 = address1,
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
                    isClosed= isClosed,
                    phone = phone,
                    url = url,
                    transactions= transactions.joinToString { "," },
                    imageUrl = imageURL,
                    distance = metersToMiles(distance),
                    rating = rating,
                    price = price,
                    address1 = location.address1,
                    reviewCount = reviewCount,
                    category = flat(categories)
                )
            }
        }
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