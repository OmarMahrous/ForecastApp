package com.alalmiyaalhura.forecastapp.data.util

import kotlinx.coroutines.flow.Flow


interface DataSource<T> {

    fun getData(): Flow<T>

    fun onError():Flow<String?>

}