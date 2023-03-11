package com.alalmiyaalhura.forecastapp.data.repository.forecast

import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.repository.BaseRepository
import com.alalmiyaalhura.forecastapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ForecastRepository : BaseRepository{

    fun getForecasts(): Flow<Resource<List<Forecast?>>>

    fun getForecastsFromLocal(): Flow<List<Forecast>>

}