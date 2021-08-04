package com.example.yelpedapp.feature.main

import com.example.yelpedapp.api.BusinessesApi
import com.example.yelpedapp.api.BusinessesApi.Companion.LOCATION_CITY
import com.example.yelpedapp.api.BusinessesApi.Companion.TERM
import com.example.yelpedapp.database.RestaurantsDao
import com.example.yelpedapp.feature.main.domain.Restaurant
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BusinessesRepository @Inject constructor(
    private val businessesApi: BusinessesApi,
    private val mapper: RestaurantsMapper,
    private val restaurantsDao: RestaurantsDao
) {

    fun getRestaurants(): Observable<List<Restaurant>> =
        restaurantsDao
            .getAll()
            .map { mapper.toDomain(it) }

    fun refreshCache(): Completable {
        return businessesApi.searchRestaurants(term = TERM, location = LOCATION_CITY)
            .map { mapper.toDBEntity(it.business) }
            .doOnSuccess {
                restaurantsDao.insertAll(it)
            }.ignoreElement()
    }
}
