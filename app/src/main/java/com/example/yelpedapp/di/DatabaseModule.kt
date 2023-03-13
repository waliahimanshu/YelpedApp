package com.example.yelpedapp.di

import android.content.Context
import androidx.room.Room
import com.example.yelpedapp.database.AppDatabase
import com.example.yelpedapp.database.RestaurantsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "restaurants-db").build()
    }

    @Provides
    @Singleton
    fun providesRestaurantDao(appDatabase: AppDatabase): RestaurantsDao {
        return appDatabase.restaurantsDao()
    }
}
