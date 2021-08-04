package com.example.yelpedapp.api

import androidx.annotation.VisibleForTesting
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * https://www.yelp.com/developers/documentation/v3/business_search
 */
interface BusinessesApi {

    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") authHeader: String = "Bearer $API_KEY",
        @Query("limit") limit :Int = 50,
        @Query("term") term: String,
        @Query("location") location: String
    ): Single<BusinessSearchResponse>

    companion object {
       @VisibleForTesting const val TERM = "restaurants"
       @VisibleForTesting const val LOCATION_CITY = "London"
        private const val API_KEY =
            "gm_pB-xbDbcG1SMv9Sw9LFq8VmGx77-bVC5XMhNC5b_uWFMb8K22-HvcfyK3ldcmmOxnWwGVwjoI1icRghq46KTkqrVGYwLNt1bODoTqTDtasVBcIeBXEmxZR__7YHYx"
    }
}