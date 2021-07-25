package com.example.yelpedapp.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BusinessesApi {

    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") authHeader: String = "Bearer $API_KEY",
        @Query("term") term: String,
        @Query("location") location: String
    ): Single<BusinessSearchResponse>

    companion object {
        private const val API_KEY =
            "gm_pB-xbDbcG1SMv9Sw9LFq8VmGx77-bVC5XMhNC5b_uWFMb8K22-HvcfyK3ldcmmOxnWwGVwjoI1icRghq46KTkqrVGYwLNt1bODoTqTDtasVBcIeBXEmxZR__7YHYx"
    }
}