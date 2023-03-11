package com.alalmiyaalhura.forecastapp.data.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository {

    fun getError(): Flow<String?>

    suspend fun fetchData()

}