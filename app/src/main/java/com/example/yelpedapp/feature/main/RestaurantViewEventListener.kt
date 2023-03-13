package com.example.yelpedapp.feature.main

import com.example.yelpedapp.feature.main.domain.Restaurant

interface RestaurantViewEventListener {
    fun onItemClicked(restaurant: Restaurant)
}
