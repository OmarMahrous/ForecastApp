package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecasts(forecastList: List<Forecast?>?)


    @Query("SELECT * FROM forecast_table")
    fun getForecastsFlow(): Flow<List<Forecast>>


    @Query("DELETE FROM forecast_table")
    suspend fun deleteAllForecasts()

}