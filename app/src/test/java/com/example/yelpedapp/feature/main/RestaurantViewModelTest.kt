package com.example.yelpedapp.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yelpedapp.feature.main.domain.Restaurant
import com.example.yelpedapp.utils.RxSchedulerRule
import com.example.yelpedapp.utils.getOrAwaitValue
import com.flextrade.kfixture.KFixture
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RestaurantViewModelTest {

    @get:Rule
    val rxRule = RxSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: RestaurantViewModel
    private val mockBusinessRepository: BusinessesRepository = mock()
    val fixture = KFixture()
    private val size = 50
    private val restaurantsFixture = List(size) { fixture<Restaurant>() }

    @Test
    fun `Given VM Init When data is success returned from DB Then Success event is emitted `() {
        whenever(mockBusinessRepository.getRestaurants())
            .thenReturn(Observable.just(restaurantsFixture))
        whenever(mockBusinessRepository.refreshCache())
            .thenReturn(Completable.complete())

        sut = RestaurantViewModel(mockBusinessRepository)

        val viewState = sut.viewState.getOrAwaitValue()
        val success = (viewState as RestaurantListViewState.Success).data
        assertThat(success).isSameInstanceAs(restaurantsFixture)
        assertThat(success.size).isEqualTo(size)
        verify(mockBusinessRepository).getRestaurants()
        verify(mockBusinessRepository).refreshCache()
        verifyNoMoreInteractions(mockBusinessRepository)
    }

    @Test
    fun `Given VM Init When no data is returned from DB Then No Success UI event is emitted `() {
        whenever(mockBusinessRepository.getRestaurants())
            .thenReturn(Observable.just(emptyList()))
        whenever(mockBusinessRepository.refreshCache())
            .thenReturn(Completable.complete())

        sut = RestaurantViewModel(mockBusinessRepository)

        val viewState = sut.viewState.getOrAwaitValue()
        Assert.assertTrue(viewState is RestaurantListViewState.Loading)
        verify(mockBusinessRepository).getRestaurants()
        verify(mockBusinessRepository).refreshCache()
        verifyNoMoreInteractions(mockBusinessRepository)
    }

    @Test
    fun `Given VM Init When data is not success returned from DB Then Success event is emitted `() {
        val message = "An error has occurred"
        whenever(mockBusinessRepository.getRestaurants())
            .thenReturn(Observable.error(Throwable(message)))
        whenever(mockBusinessRepository.refreshCache())
            .thenReturn(Completable.complete())

        sut = RestaurantViewModel(mockBusinessRepository)

        val viewState = sut.viewState.getOrAwaitValue()
        val error = (viewState as RestaurantListViewState.Error)
        assertThat(error.message).isEqualTo(message)
        verify(mockBusinessRepository).getRestaurants()
        verify(mockBusinessRepository).refreshCache()
        verifyNoMoreInteractions(mockBusinessRepository)
    }

    @Test
    fun `Given VM Init When data is success synced from network Then Success refresh event is emitted`() {
        whenever(mockBusinessRepository.getRestaurants())
            .thenReturn(Observable.just(restaurantsFixture))
        whenever(mockBusinessRepository.refreshCache())
            .thenReturn(Completable.complete())

        sut = RestaurantViewModel(mockBusinessRepository)

        val refreshState = sut.refreshingViewState.getOrAwaitValue()
        Assert.assertTrue(refreshState is RestaurantRefreshState.SuccessRefresh)
    }

    @Test
    fun `Given VM Init When data is not success synced from network Then Error refresh event is emitted`() {
        whenever(mockBusinessRepository.getRestaurants())
            .thenReturn(Observable.just(restaurantsFixture))
        val message = "An error has occurred"
        whenever(mockBusinessRepository.refreshCache())
            .thenReturn(Completable.error(Throwable(message)))

        sut = RestaurantViewModel(mockBusinessRepository)

        val refreshState = sut.refreshingViewState.getOrAwaitValue()
        val error = (refreshState as RestaurantRefreshState.ErrorRefresh)
        assertThat(error.message).isEqualTo(message)
        verify(mockBusinessRepository).getRestaurants()
        verify(mockBusinessRepository).refreshCache()
        verifyNoMoreInteractions(mockBusinessRepository)
    }
}
