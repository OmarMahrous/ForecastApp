package com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast

import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.util.DataSource
import com.alalmiyaalhura.forecastapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

class ForecastRemoteSource : DataSource<Resource<List<Forecast>>> {

    override fun getData(): Flow<Resource<List<Forecast>>> {
//        TODO("Not yet implemented")
    }

    override fun onError(): Flow<String?> {
//        TODO("Not yet implemented")
    }

}