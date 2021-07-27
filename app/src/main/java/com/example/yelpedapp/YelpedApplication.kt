package com.example.yelpedapp

import android.app.Application
import com.example.yelpedapp.di.networkModule
import com.example.yelpedapp.feature.main.di.businessModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YelpedApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                networkModule,
                businessModule
            )
        }
    }
}