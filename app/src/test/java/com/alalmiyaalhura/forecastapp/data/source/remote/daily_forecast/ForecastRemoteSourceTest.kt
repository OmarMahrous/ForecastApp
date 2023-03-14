package com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast

import android.content.ContentValues.TAG
import android.util.Log
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.repository.forecast.ForecastRepository
import com.alalmiyaalhura.forecastapp.data.repository.forecast.ForecastRepositoryImpl
import com.alalmiyaalhura.forecastapp.data.source.remote.api_service.ApiGenerator
import com.google.common.truth.ExpectFailure.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.create
import java.net.HttpURLConnection

//import org.junit.jupiter.api.Assertions.*

class ForecastRemoteSourceTest {

    private var remoteSource: ForecastRemoteSource?=null
    private var forecastApi: ForecastApi?=null

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        forecastApi = ApiGenerator.initRetrofit().create(ForecastApi::class.java)
        remoteSource = ForecastRemoteSource(forecastApi!!)
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

        forecastApi = null
        remoteSource = null
    }

}