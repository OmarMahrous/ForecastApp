package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.source.local.database.type_converter.MyTypeConverter

@Database(entities = [Forecast::class], version = 3)
@TypeConverters(value = [MyTypeConverter::class])
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao


}