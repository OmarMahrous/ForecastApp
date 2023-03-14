package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.alalmiyaalhura.forecastapp.ForecastApp
import com.alalmiyaalhura.forecastapp.data.source.remote.api_service.ApiGenerator
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastRemoteSource
import com.alalmiyaalhura.forecastapp.data.util.ListGenerator
import com.google.common.truth.ExpectFailure.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class ForecastLocalSourceTest{

    private var localSource: ForecastLocalSource?=null

    private lateinit var forecastDb:ForecastDatabase
    private var forecastDao:ForecastDao?=null

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        forecastDb = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ForecastDatabase::class.java)
            .allowMainThreadQueries().build()
        forecastDao = forecastDb.forecastDao()
        localSource = ForecastLocalSource(forecastDao!!)
    }

    @Test
    fun insertForecasts_returnsTrue() = runTest {


//        launch (Dispatchers.Main) {

            val forecastList = ListGenerator.createRandomForecastList()


            localSource?.saveForecasts(forecastList)

            val actualList = localSource?.getData()?.test {
                val list = awaitItem()
                assert(list.contains(forecastList[0]))
                assert(list.contains(forecastList[1]))
                cancel()
            }

//            assert(actualList?.isEmpty() == false)
//            assert(actualList == forecastList)
//            assert(actualList?.size == forecastList.size)
//            assertThat(AssertionError(actualList?.size)).isEqualTo(forecastList.size)

//        }
    }

//    @Test
//    fun insertWord_returnsTrue() = runTest {
//        val forecastList = ListGenerator.createRandomForecastList()
//
//        localSource?.saveForecasts(forecastList)
//
//
//        val actual = localSource?.getData()?.first()
////            forecastDao?.getForecastsFlow()?.collect {
//        assertThat(AssertionError(actual?.size)).isEqualTo(forecastList.size)
//
////            }
//    }

//    @Test
//    fun `for cityName not available or invalid, api must return with http code 404 not found`() = runTest {

//        launch (Dispatchers.Main) {
//            remoteSource?.fetch("fakeCity")

//            val responseError = remoteSource?.onError()?.first()
//            val actualResponse = remoteSource?.getData()?.first()?.data

//            assert(responseError != null)
//            assert(actualResponse == null)

//        }
//    }



    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()

        forecastDb.close()
        forecastDao = null
        localSource = null
    }

}