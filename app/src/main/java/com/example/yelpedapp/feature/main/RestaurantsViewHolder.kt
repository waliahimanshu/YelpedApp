package com.example.yelpedapp.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yelpedapp.databinding.RestaurantListItemBinding
import com.example.yelpedapp.feature.main.domain.Restaurants

class RestaurantsViewHolder(private val binding: RestaurantListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(item: Restaurants) {

        Glide.with(binding.root.context)
            .load(item.imageUrl)
            .into(binding.restaurantImageView)

        binding.restaurantName.text = item.name
        binding.addressTextView.text = item.address1
        binding.ratingBar.rating = item.rating.toFloat()
        binding.distanceTextView.text = "${item.distance} miles"
        binding.totalReviewsTextView.text = "Based on ${item.reviewCount} reviews"
        binding.priceTextView.text = item.price
        binding.categoryTextView.text = item.category

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