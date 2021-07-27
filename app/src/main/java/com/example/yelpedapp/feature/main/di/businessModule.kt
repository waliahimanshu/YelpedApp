package com.example.yelpedapp.feature.main.di

import com.example.yelpedapp.api.BusinessesApi
import com.example.yelpedapp.feature.main.BusinessesRepository
import com.example.yelpedapp.feature.main.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val businessModule = module {

    viewModel {
        RestaurantViewModel(
            businessesRepository = get()
        )
    }

    single {
        get<Retrofit>().create(BusinessesApi::class.java)
    }

    single {
        BusinessesRepository(businessesApi = get())
    }
}
