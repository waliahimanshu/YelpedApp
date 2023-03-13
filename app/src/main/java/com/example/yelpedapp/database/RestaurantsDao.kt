package com.example.yelpedapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface RestaurantsDao {

    @Query("SELECT * from restaurants")
    fun getAll(): Observable<List<RestaurantsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<RestaurantsEntity>)

    @Query("DELETE from restaurants")
    fun deleteAll()
}
