package com.example.yelpedapp.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yelpedapp.feature.main.domain.Restaurants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RestaurantViewModel(private val businessesRepository: BusinessesRepository) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _viewState = MutableLiveData<RestaurantListViewState>()
    val viewState: LiveData<RestaurantListViewState> = _viewState

    init {
        disposable.add(
            businessesRepository.getRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _viewState.value = RestaurantListViewState.Success(it)
                },
                    {
                        val cause = it.cause
                    })
        )

    }
}

sealed class RestaurantListViewState {
    data class Success(val list: List<Restaurants>) : RestaurantListViewState()
    object Loading : RestaurantListViewState()
    object Error : RestaurantListViewState()
}