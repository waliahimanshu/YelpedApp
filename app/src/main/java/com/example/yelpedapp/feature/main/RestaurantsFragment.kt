package com.example.yelpedapp.feature.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yelpedapp.R
import com.example.yelpedapp.databinding.FragmentRestaurantsBinding
import com.example.yelpedapp.feature.main.domain.Restaurant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantsFragment : Fragment(R.layout.fragment_restaurants), RestaurantViewEventListener {

    private val viewModel by viewModels<RestaurantViewModel>()
    private var _binding: FragmentRestaurantsBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantsListAdapter: RestaurantsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRestaurantsBinding.bind(view)

        binding.swipeToRefresh.apply {
            setOnRefreshListener {
                viewModel.onViewEvent(RestaurantViewEvents.Refresh)
            }
            isRefreshing = false
        }
        restaurantsListAdapter = RestaurantsListAdapter(this)
        binding.restaurantsList.apply {
            adapter = restaurantsListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer(::observeViewStates))
        viewModel.refreshingViewState.observe(viewLifecycleOwner, Observer(::observeRefreshingStates))
    }

    private fun observeRefreshingStates(state: RestaurantRefreshState) {
        when (state) {
            is RestaurantRefreshState.SuccessRefresh -> {
                binding.errorLoadingView.hide()
                binding.swipeToRefresh.isRefreshing = false
            }
            is RestaurantRefreshState.ErrorRefresh -> {
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                binding.errorLoadingView.showError()
                binding.swipeToRefresh.isRefreshing = false
            }
        }
    }

    private fun observeViewStates(viewState: RestaurantListViewState) {
        when (viewState) {
            is RestaurantListViewState.Success -> {
                binding.errorLoadingView.hide()
                restaurantsListAdapter.submitList(viewState.data)
                binding.swipeToRefresh.isRefreshing = false
            }
            is RestaurantListViewState.Error -> {
                binding.errorLoadingView.showError()
                binding.swipeToRefresh.isRefreshing = false
                Toast.makeText(
                    requireContext(),
                    viewState.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            RestaurantListViewState.Loading -> {
                binding.errorLoadingView.showLoading()
                binding.swipeToRefresh.isRefreshing = true
            }
            RestaurantListViewState.Empty -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(restaurant: Restaurant) {
        val args = bundleOf(RESTAURANT_DETAIL to restaurant)
        findNavController().navigate(R.id.action_BusinessFragment_to_DetailFragment, args)
    }

    companion object {
        @VisibleForTesting const val RESTAURANT_DETAIL = "restaurant"
    }
}
