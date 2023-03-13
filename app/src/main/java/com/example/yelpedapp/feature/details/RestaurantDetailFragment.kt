package com.example.yelpedapp.feature.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.yelpedapp.R
import com.example.yelpedapp.databinding.FragmentRestaurantDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment(R.layout.fragment_restaurant_details) {

    private val viewModel by viewModels<RestaurantDetailsViewModel>()

    private var _binding: FragmentRestaurantDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRestaurantDetailsBinding.bind(view)

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::observerViewState))
    }

    private fun observerViewState(state: RestaurantDetailsViewState) {
        when (state) {
            is RestaurantDetailsViewState.Success -> {
                binding.errorLoadingView.hide()
                val data = state.data
                setUpMainImage(data.imageUrl)
                binding.aliasTextView.text = data.alias
                binding.nameTextView.text = data.name
                binding.phoneNumberTextVew.text = data.phone
                binding.priceTextView.text = data.price
                binding.fullAddressTextView.text = data.address
                binding.distanceTextView.text = getString(R.string.distance_in_miles, data.distance)
                setUpYelpUrl(data.url)
            }
        }
    }

    private fun setUpMainImage(imageUrl: String) {
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.restaurantMainImageView)
    }

    private fun setUpYelpUrl(url: String) {
        binding.urlTextView.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                ),
                null
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
