package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alalmiyaalhura.forecastapp.data.model.Forecast

@Database(entities = [Forecast::class], version = 1)
abstract class LeagueDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao


}