package com.example.yelpedapp.feature.main

import com.example.yelpedapp.api.BusinessesApi
import com.example.yelpedapp.api.CategoryDTO
import com.example.yelpedapp.feature.main.domain.Restaurants
import io.reactivex.Single

class BusinessesRepository(private val businessesApi: BusinessesApi) {

    fun getRestaurants(): Single<List<Restaurants>> =
        businessesApi.searchRestaurants(term = "restaurants", location = "London")
            .map { searchResponse ->
                searchResponse.business.map { businessDTO ->
                    Restaurants(
                        id = businessDTO.id,
                        name = businessDTO.name,
                        imageUrl = businessDTO.imageURL,
                        distance = metersToMiles(businessDTO.distance),
                        rating = businessDTO.rating,
                        category = flat(businessDTO.categories),
                        price = businessDTO.price,
                        address1 = businessDTO.location.address1,
                        reviewCount = businessDTO.reviewCount
                    )
                }
            }

    private fun flat(categories: List<CategoryDTO>): String {
        return categories.joinToString(", ") {
            it.title
        }
    }

    private fun metersToMiles(totalDistanceInMeters: Double): String {
        val milesInMeter = 0.000621371
        return String.format("%.2f", totalDistanceInMeters * milesInMeter)
    }
}
