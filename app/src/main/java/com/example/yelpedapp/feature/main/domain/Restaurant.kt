package com.example.yelpedapp.feature.main.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val id: String,
    val name: String,
    val imageUrl: String,
    val distance: String,
    val rating: Float,
    val price: String?,
    val address: String,
    val reviewCount: String,
    val category: String,
    val alias: String,
    val url: String,
    val phone: String
): Parcelable