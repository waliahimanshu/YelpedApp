package com.example.yelpedapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YelpedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
