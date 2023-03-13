package com.example.yelpedapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "distance") val distance: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "reviewCount") val reviewCount: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "alias") val alias: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "phone") val phone: String
)
