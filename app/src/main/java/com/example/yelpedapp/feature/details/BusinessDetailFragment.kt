package com.example.yelpedapp.feature.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.yelpedapp.R
import com.example.yelpedapp.databinding.FragmentRestaurantDetailsBinding
import com.example.yelpedapp.feature.main.BusinessesFragment.Companion.BUSINESS_ID
import com.example.yelpedapp.feature.main.BusinessesFragment.Companion.REQUEST_KEY
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class BusinessDetailFragment : Fragment(R.layout.fragment_restuarants_details) {

    private val viewModel by viewModels<RestaurantDetailsViewModel>()

    private var _binding  : FragmentRestaurantDetailsBinding? = null
    private val binding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            viewModel.businessId = bundle.getString(BUSINESS_ID)
                ?: throw IllegalArgumentException("id received is null")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding  = FragmentRestaurantDetailsBinding.bind(view)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}