package com.example.yelpedapp.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yelpedapp.feature.main.domain.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val businessesRepository: BusinessesRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()

    private val _viewState = MutableLiveData<RestaurantListViewState>()
    val viewState: LiveData<RestaurantListViewState> = _viewState

    private val _refreshingViewState = MutableLiveData<RestaurantRefreshState>()
    val refreshingViewState: LiveData<RestaurantRefreshState> = _refreshingViewState

    init {
        getDataFromLocalDb()
        refreshFromNetwork()
    }
    fun onViewEvent(restaurantViewEvents: RestaurantViewEvents) {
        when (restaurantViewEvents) {
            RestaurantViewEvents.Refresh -> {
                _viewState.value = RestaurantListViewState.Loading
                refreshFromNetwork()
            }
        }
    }

    private fun getDataFromLocalDb() {
        disposable +=
            businessesRepository.getRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _viewState.value = RestaurantListViewState.Loading
                }
                .subscribeBy(
                    onNext = {
                        if(!it.isNullOrEmpty()) {
                            _viewState.value = RestaurantListViewState.Success(it)
                        }
                    },
                    onError = {
                        _viewState.value =
                            RestaurantListViewState.Error(getErrorMessage(it))
                    })
    }


    private fun refreshFromNetwork() {
        disposable +=
            businessesRepository.refreshCache()
                .toSingleDefault(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        _refreshingViewState.value = RestaurantRefreshState.SuccessRefresh
                    },
                    onError = {
                        _refreshingViewState.value = RestaurantRefreshState.ErrorRefresh(getErrorMessage(it))
                    })
    }

    private fun getErrorMessage(it: Throwable) = it.message ?: "An error has occurred"

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

sealed class RestaurantViewEvents {
    object Refresh  : RestaurantViewEvents()
}

sealed class RestaurantRefreshState {
    object SuccessRefresh : RestaurantRefreshState()
    data class ErrorRefresh(val message: String) : RestaurantRefreshState()
}

sealed class RestaurantListViewState {
    data class Success(val list: List<Restaurant>) : RestaurantListViewState()
    object Loading : RestaurantListViewState()
    data class Error(val message: String) : RestaurantListViewState()
}