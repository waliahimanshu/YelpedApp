package com.example.yelpedapp.feature.details

import androidx.lifecycle.SavedStateHandle
import com.example.yelpedapp.feature.main.RestaurantsFragment.Companion.RESTAURANT_DETAIL
import com.example.yelpedapp.feature.main.domain.Restaurant
import com.example.yelpedapp.utils.getOrAwaitValue
import com.flextrade.kfixture.KFixture
import com.google.common.truth.Truth
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class RestaurantDetailsViewModelTest {

    private lateinit var sut: RestaurantDetailsViewModel
    private val savedStateHandle = SavedStateHandle()
    val fixture = KFixture()

    @Test
    fun `When save state handle has Data Then emmit Success event`() {
        val restaurantFixture = fixture<Restaurant>()
        savedStateHandle[RESTAURANT_DETAIL] = restaurantFixture

        sut = RestaurantDetailsViewModel(savedStateHandle)

        val detailsViewState = sut.viewState.getOrAwaitValue()
        val success = (detailsViewState as RestaurantDetailsViewState.Success).data
        Truth.assertThat(success).isSameInstanceAs(restaurantFixture)
        Truth.assertThat(success.id).isEqualTo(restaurantFixture.id)
    }

    @Test
    fun `When save state handle has no Data Then throw exception and fail fast`() {
        savedStateHandle[RESTAURANT_DETAIL] = null
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                RestaurantDetailsViewModel(savedStateHandle)
            }

        assertEquals("Business id not passed", exception.message);
    }
}