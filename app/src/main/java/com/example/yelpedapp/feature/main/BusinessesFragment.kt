package com.example.yelpedapp.feature.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yelpedapp.R
import com.example.yelpedapp.databinding.FragmentRestaurantsBinding
import com.example.yelpedapp.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessesFragment : Fragment(R.layout.fragment_restaurants), RestaurantViewEventListener {

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
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::observeViewStates))

    }

    private fun observeViewStates(viewState: RestaurantListViewState) {
        when (viewState) {
            is RestaurantListViewState.Success -> {
                restaurantsListAdapter.submitList(viewState.list)
                binding.swipeToRefresh.isRefreshing = false
            }
            is RestaurantListViewState.Error -> {
                binding.swipeToRefresh.isRefreshing = false
                Toast.makeText(
                    requireContext(),
                    viewState.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            RestaurantListViewState.Loading -> {
                binding.swipeToRefresh.isRefreshing = true
            }
        }.exhaustive
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(businessId: String) {
        setFragmentResult(REQUEST_KEY, bundleOf( BUSINESS_ID to businessId))
        findNavController().navigate(R.id.action_BusinessFragment_to_DetailFragment)
    }

    companion object {
         const val BUSINESS_ID = "BUSINESS_ID"
         const val REQUEST_KEY = "REQUEST_KEY"
    }
}