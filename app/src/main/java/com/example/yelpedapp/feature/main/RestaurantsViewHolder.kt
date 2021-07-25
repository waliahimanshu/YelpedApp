package com.example.yelpedapp.feature.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yelpedapp.databinding.RestaurantListItemBinding
import com.example.yelpedapp.feature.main.domain.Restaurants

class RestaurantsViewHolder(private val binding: RestaurantListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(item: Restaurants) {
        binding.restaurantImageView.setImageURI(Uri.parse(item.imageUrl))
        binding.restaurantName.text = item.name

    }

    companion object {
        fun create(parent: ViewGroup): RestaurantsViewHolder {
            val restaurantListItemBinding =
                RestaurantListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

            return RestaurantsViewHolder(restaurantListItemBinding)
        }
    }
}