package com.example.yelpedapp.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.yelpedapp.feature.main.BusinessesFragment.Companion.RESTAURANT_DETAIL
import com.example.yelpedapp.feature.main.domain.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    var restaurant: Restaurant = savedStateHandle.get(RESTAURANT_DETAIL)
        ?: throw IllegalArgumentException("Business id not passed")

    private val _viewState = MutableLiveData<RestaurantDetailsViewState>()
    val viewState: LiveData<RestaurantDetailsViewState> = _viewState

    init {
        _viewState.value = RestaurantDetailsViewState.Success(restaurant)
    }
}

sealed class RestaurantDetailsViewState {
    data class Success(val data: Restaurant) : RestaurantDetailsViewState()
    object Loading : RestaurantDetailsViewState()
    object Error : RestaurantDetailsViewState()
}
