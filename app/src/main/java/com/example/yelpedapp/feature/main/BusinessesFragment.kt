package com.example.yelpedapp.feature.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yelpedapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_restaurants.restaurantsList

@AndroidEntryPoint
class BusinessesFragment : Fragment(R.layout.fragment_restaurants) {

    private val viewModel by viewModels<RestaurantViewModel>()
    private lateinit var restaurantsListAdapter: RestaurantsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restaurantsListAdapter = RestaurantsListAdapter()
        restaurantsList.apply {
            adapter = restaurantsListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::observeViewStates))
    }

    private fun observeViewStates(viewState: RestaurantListViewState) {
        when (viewState) {
            is RestaurantListViewState.Success -> {
                restaurantsListAdapter.submitList(viewState.list)
            }
            RestaurantListViewState.Error -> TODO()
            RestaurantListViewState.Loading -> TODO()
        }
    }
}