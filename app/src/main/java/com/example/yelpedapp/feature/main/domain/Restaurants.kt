package com.example.yelpedapp.feature.main.domain

data class Restaurants(
    val id: String,
    val name: String,
    val imageUrl: String,
    val distance: String,
    val rating: Double,
    val price: String?,
    val address1: String,
    val reviewCount: Long,
    val category: String
)