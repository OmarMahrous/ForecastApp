package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.Room
import com.alalmiyaalhura.forecastapp.ForecastApp
import com.alalmiyaalhura.forecastapp.data.source.remote.api_service.ApiGenerator
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastRemoteSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class ForecastLocalSourceTest{

    private var localSource: ForecastLocalSource?=null

    private lateinit var forecastDb:ForecastDatabase
    private var forecastDao:ForecastDao?=null

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        forecastDb = Room.inMemoryDatabaseBuilder(ForecastApp.getAppContext(), ForecastDatabase::class.java)
            .allowMainThreadQueries().build()
        forecastDao = forecastDb.forecastDao()
        localSource = ForecastLocalSource(forecastDao!!)
    }

    @Test
    fun `for multiple forecasts, api must return all forecasts with http code 200`() = runTest {


        launch (Dispatchers.Main) {
            remoteSource?.fetch("cairo")

            val responseError = remoteSource?.onError()?.first()
            val actualResponse = remoteSource?.getData()?.first()?.data


            assert(responseError == null)
            assert(actualResponse != null)

        }
    }

    @Test
    fun `for cityName not available or invalid, api must return with http code 404 not found`() = runTest {

        launch (Dispatchers.Main) {
            remoteSource?.fetch("fakeCity")

            val responseError = remoteSource?.onError()?.first()
            val actualResponse = remoteSource?.getData()?.first()?.data


            assert(responseError != null)
            assert(actualResponse == null)

        }
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()

        forecastDb.close()
        forecastDao = null
        localSource = null
    }

}