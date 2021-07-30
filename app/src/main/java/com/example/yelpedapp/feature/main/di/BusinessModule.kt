package com.example.yelpedapp.feature.main.di

import com.example.yelpedapp.api.BusinessesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BusinessModule {

    @Provides
    @Singleton
    fun providesBusinessApiService(retrofit: Retrofit): BusinessesApi {
        return retrofit.create(BusinessesApi::class.java)
    }
}
