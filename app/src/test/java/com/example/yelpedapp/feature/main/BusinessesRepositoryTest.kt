package com.example.yelpedapp.feature.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yelpedapp.api.BusinessDTO
import com.example.yelpedapp.api.BusinessSearchResponse
import com.example.yelpedapp.api.BusinessesApi
import com.example.yelpedapp.api.BusinessesApi.Companion.LOCATION_CITY
import com.example.yelpedapp.api.BusinessesApi.Companion.TERM
import com.example.yelpedapp.database.RestaurantsDao
import com.example.yelpedapp.database.RestaurantsEntity
import com.example.yelpedapp.feature.main.domain.Restaurant
import com.example.yelpedapp.utils.RxSchedulerRule
import com.flextrade.kfixture.KFixture
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class BusinessesRepositoryTest {

    @get:Rule
    val rxRule = RxSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockRestaurantDao: RestaurantsDao = mock()
    private val mockMapper: RestaurantsMapper = mock()
    private val mockBusinessAPI: BusinessesApi = mock()

    private lateinit var sut: BusinessesRepository
    val fixture = KFixture()
    private val size = 50
    private val restaurantsEntityFixture = List(size) { fixture<RestaurantsEntity>() }
    private val restaurantsFixture = List(size) { fixture<Restaurant>() }

    @Test
    fun `Given local db has a data When request Then return list of restaurants `() {
        whenever(mockRestaurantDao.getAll()).thenReturn(Observable.just(restaurantsEntityFixture))
        whenever(mockMapper.toDomain(restaurantsEntityFixture)).thenReturn(restaurantsFixture)
        sut = BusinessesRepository(
            businessesApi = mockBusinessAPI,
            mapper = mockMapper,
            restaurantsDao = mockRestaurantDao
        )
        val testObserver = sut.getRestaurants().test()

        verify(mockRestaurantDao).getAll()
        verify(mockMapper).toDomain(restaurantsEntityFixture)
        testObserver.assertValue(restaurantsFixture)
        verify(mockBusinessAPI, never())
    }


    @Test
    fun `Given network refresh When request refreshThen return list of restaurants from the network `() {
         val restaurantsDTOFixture = List(size) { fixture<BusinessDTO>() }
        val response = fixture<BusinessSearchResponse>()
        whenever(
            mockBusinessAPI.searchRestaurants(
                term = TERM,
                location = LOCATION_CITY
            )
        ).thenReturn(
            Single.just(response)
        )
        whenever(mockMapper.toDBEntity(restaurantsDTOFixture)).thenReturn(restaurantsEntityFixture)

        sut = BusinessesRepository(
            businessesApi = mockBusinessAPI,
            mapper = mockMapper,
            restaurantsDao = mockRestaurantDao
        )
        val testObserver = sut.refreshCache().test()

        testObserver.assertComplete().assertNoErrors()
        verify(mockBusinessAPI).searchRestaurants(term = TERM, location = LOCATION_CITY)
    }
}