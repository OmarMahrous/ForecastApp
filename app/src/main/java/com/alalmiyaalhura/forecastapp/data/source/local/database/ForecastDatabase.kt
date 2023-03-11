package com.alalmiyaalhura.forecastapp.data.source.local.database

import androidx.room.RoomDatabase

abstract class LeagueDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao


}