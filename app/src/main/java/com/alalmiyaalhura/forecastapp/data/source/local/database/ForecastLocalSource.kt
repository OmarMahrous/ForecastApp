package com.alalmiyaalhura.forecastapp.data.source.local.database

import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.util.DataSource
import kotlinx.coroutines.flow.Flow

class ForecastLocalSource :DataSource<List<Forecast>>{

    override fun getData(): Flow<List<Forecast>> {
//        TODO("Not yet implemented")
    }

    override fun onError(): Flow<String?> {
//        TODO("Not yet implemented")
    }
}