package com.alalmiyaalhura.forecastapp.data.repository.forecast

import android.util.Log
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastDao
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastLocalSource
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastRemoteSource
import com.alalmiyaalhura.forecastapp.data.util.Resource
import com.alalmiyaalhura.forecastapp.data.util.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf

class ForecastRepositoryImpl : ForecastRepository{

    private val TAG = "ForecastRepositoryImpl"


    private val cityName:String

    private val remoteDataSource: ForecastRemoteSource
    private val localDataSource: ForecastLocalSource

    private val coroutineScope: CoroutineScope

    constructor(
        cityName:String,
        remoteDataSource: ForecastRemoteSource,
        localDataSource: ForecastLocalSource,
        coroutineScope: CoroutineScope
    )
    {
        this.cityName = cityName

        this.remoteDataSource = remoteDataSource
        this.localDataSource = localDataSource

        this.coroutineScope = coroutineScope
    }

    companion object{
        fun create(forecastApi: ForecastApi, forecastDao: ForecastDao, cityName: String, coroutineScope: CoroutineScope): ForecastRepository {
            val remoteDataSource = ForecastRemoteSource(forecastApi)
            val localDataSource = ForecastLocalSource(forecastDao)
            return ForecastRepositoryImpl(cityName, remoteDataSource, localDataSource, coroutineScope)
        }

    }


    override fun getForecastsFromLocal(): Flow<List<Forecast>> {


        return try{
            val localData = localDataSource.getData() ?: flowOf()


            localData

        }catch (e:Exception){
            Log.e(TAG, "getForecastsFromLocal: error = ${e.message}" )

            flowOf()
        }

    }

    override fun getForecasts(): Flow<Resource<List<Forecast?>>> {
        return remoteDataSource.getData() ?: flowOf()
    }

    override fun getError(): Flow<String?> {
        return remoteDataSource.onError()

    }

    override suspend fun fetchData() {
        remoteDataSource.fetch(cityName)

        loadResultFromRemote()
    }

    private suspend fun loadResultFromRemote() {
        val forecastsFromRemote = getForecasts()

        forecastsFromRemote.collectLatest {
            when(it.status){
                Status.SUCCESS -> saveForecastsInCache(it.data)

                Status.ERROR -> Log.e(TAG, "loadResultFromRemote: ${it.message}" )

                else-> Log.d(TAG, "loadResultFromRemote: loading")
            }
        }

    }

    private suspend fun saveForecastsInCache(forecastList: List<Forecast?>?) {

        localDataSource.deleteAllForecasts() // Reset data
        localDataSource.saveForecasts(forecastList)
    }



}