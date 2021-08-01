package com.example.yelpedapp.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.yelpedapp.feature.main.BusinessesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val businessesRepository: BusinessesRepository
) :
    ViewModel() {

    private val disposable  = CompositeDisposable()
    var businessId: String
        get() = savedStateHandle.get<String>(BUSINESS_ID)
            ?: throw IllegalArgumentException("businessId is not set")
        set(value) = savedStateHandle.set(BUSINESS_ID, value)


    init {
        disposable += businessesRepository.getRestaurantById(businessId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {

                },
                onError = {

                }
            )
    }

    companion object {
        private const val BUSINESS_ID = "BUSINESS_ID"
    }
}