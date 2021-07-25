package com.example.yelpedapp.feature.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.yelpedapp.feature.main.domain.Restaurants

class RestaurantsListAdapter : ListAdapter<Restaurants, RestaurantsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        return RestaurantsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Restaurants>() {
            override fun areItemsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
                return oldItem == newItem
            }
        }

    }
}


