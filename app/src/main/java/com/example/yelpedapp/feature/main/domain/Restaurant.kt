package com.example.yelpedapp.feature.main.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val id: String,
    val name: String,
    val imageUrl: String,
    val distance: String,
    val rating: Float,
    val price: String?,
    val address1: String,
    val reviewCount: String,
    val category: String,
    val alias: String,
    val isClosed: Boolean,
    val url: String,
    val transactions: String,
    val phone: String
) : Parcelable