package com.alalmiyaalhura.forecastapp.data.source.local.database

import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.util.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ForecastLocalSource(val forecastDao: ForecastDao) : DataSource<List<Forecast>>{

    private val mError: MutableStateFlow<String?> = MutableStateFlow("")




    suspend fun saveForecasts(forecastList: List<Forecast?>?) {
        try {
            forecastDao.insertForecasts(forecastList)
        } catch (e: Exception) {
            e.printStackTrace()
            mError.value = (e.message)
        }
    }



    suspend fun deleteAllForecasts(){
        forecastDao.deleteAllForecasts()
    }



    override fun getData(): Flow<List<Forecast>> {

        return forecastDao.getForecastsFlow()

    }

    override fun onError(): Flow<String?> {
        return mError
    }


}