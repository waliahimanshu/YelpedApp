package com.example.yelpedapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RestaurantsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantsDao(): RestaurantsDao
}
