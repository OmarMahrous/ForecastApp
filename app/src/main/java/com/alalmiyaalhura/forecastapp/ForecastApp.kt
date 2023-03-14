package com.alalmiyaalhura.forecastapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ForecastApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }

    companion object{
        private lateinit var appContext: Context

        public fun getAppContext(): Context {
            return appContext
        }
    }

}