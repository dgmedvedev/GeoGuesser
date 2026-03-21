package com.example.geoguesser

import android.app.Application
import com.example.geoguesser.core.di.AppContainer

class GeoGuesserApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
    }
}
