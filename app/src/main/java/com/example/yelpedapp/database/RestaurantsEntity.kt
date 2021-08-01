package com.example.yelpedapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "distance") val distance: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "address1") val address1: String,
    @ColumnInfo(name = "reviewCount") val reviewCount: Int,
    @ColumnInfo(name = "category") val category: String
)