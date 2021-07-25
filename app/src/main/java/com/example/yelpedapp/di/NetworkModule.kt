package com.example.yelpedapp.di

import com.example.yelpedapp.api.BusinessesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_UR = "https://api.yelp.com/v3/"

val networkModule = module {

    single {
        provideHttpClient(provideLoggingInterceptor())
    }

    single {
        provideRetrofit(httpClient = get(), moshi = provideMoshi())
    }

    single {
        get<Retrofit>().create(BusinessesApi::class.java)
    }
}

fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
     .baseUrl(BASE_UR)
     .client(httpClient)
     .addConverterFactory(MoshiConverterFactory.create(moshi))
     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
     .build()

fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
