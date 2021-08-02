package com.example.yelpedapp.feature.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.yelpedapp.R
import com.example.yelpedapp.databinding.FragmentRestaurantDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessDetailFragment : Fragment(R.layout.fragment_restaurant_details) {

    private val viewModel by viewModels<RestaurantDetailsViewModel>()

    private var _binding  : FragmentRestaurantDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding  = FragmentRestaurantDetailsBinding.bind(view)

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::observerViewState))
    }

    private fun observerViewState(state: RestaurantDetailsViewState) {
        when (state) {
            RestaurantDetailsViewState.Error -> {
                Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT).show()
            }
            is RestaurantDetailsViewState.Success -> {
                val data = state.data
                Glide.with(requireContext())
                    .load(data.imageUrl)
                    .into(binding.restaurantMainImageView)

                binding.aliasTextView.text = data.alias
                binding.nameTextView.text = data.name
                binding.phoneNumberTextVew.text = data.phone
                binding.isClosedTextView.text = data.isClosed.toString()
                binding.transactionsTextView.text = data.transactions
            }
            RestaurantDetailsViewState.Loading -> TODO()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}