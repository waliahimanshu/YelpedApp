package com.example.yelpedapp.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yelpedapp.R
import com.example.yelpedapp.databinding.RestaurantListItemBinding
import com.example.yelpedapp.feature.main.domain.Restaurant

class RestaurantsViewHolder(
    private val binding: RestaurantListItemBinding,
    private val eventListener: RestaurantViewEventListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(item: Restaurant) {

        val context = binding.root.context
        Glide.with(context)
            .load(item.imageUrl)
            .into(binding.restaurantImageView)

        binding.restaurantName.text = item.name
        binding.addressTextView.text = item.address
        binding.ratingBar.rating = item.rating
        binding.distanceTextView.text =
            context.getString(R.string.distance_in_miles, item.distance)
        binding.totalReviewsTextView.text =
            context.getString(R.string.based_on_no_of_reviews, item.reviewCount)
        binding.priceTextView.text = item.price
        binding.categoryTextView.text = item.category

        binding.root.setOnClickListener {
            eventListener.onItemClicked(item)
        }

    }

    companion object {
        fun create(parent: ViewGroup, eventListener: RestaurantViewEventListener): RestaurantsViewHolder {
            val restaurantListItemBinding =
                RestaurantListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

            return RestaurantsViewHolder(restaurantListItemBinding, eventListener)
        }
    }
}