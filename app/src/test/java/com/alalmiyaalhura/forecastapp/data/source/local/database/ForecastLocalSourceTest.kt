package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.util.ListGenerator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
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

            val forecastList = ListGenerator.createRandomForecastList()


            localSource?.saveForecasts(forecastList)

            localSource?.getData()?.test {
                val list = awaitItem()
                assert(list.contains(forecastList[0]))
                assert(list.contains(forecastList[1]))
                cancel()
            }
    }

    @Test
    fun `insert forecasts with duplicated values, function should replace conflict`() = runTest {


        val duplicatedForecastList = ListGenerator.createForecastListWithDuplication()


        localSource?.saveForecasts(duplicatedForecastList)

        localSource?.getData()?.test {
            val list = awaitItem()
            assert(list.size == 2)
            assert(list != duplicatedForecastList)
            cancel()
        }
    }

    @Test
    fun insertEmptyForecastList() = runTest {
        val emptyForecastList = emptyList<Forecast>()


        localSource?.saveForecasts(emptyForecastList)

        localSource?.getData()?.test {
            val list = awaitItem()
            assert(list.isEmpty())
            assert(list == emptyForecastList)
            cancel()
        }
    }

    @Test
    fun deleteForecastList() = runTest {

        val forecastList = ListGenerator.createRandomForecastList()


        localSource?.saveForecasts(forecastList)

        localSource?.deleteAllForecasts()

        localSource?.getData()?.test {
            val list = awaitItem()
            assert(list.isEmpty())
            assert(list != forecastList)
            cancel()
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