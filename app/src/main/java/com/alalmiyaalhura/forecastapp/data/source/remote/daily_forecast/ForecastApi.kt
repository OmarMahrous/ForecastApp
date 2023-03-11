package com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast")
    suspend fun getDailyForecast(@Query("q") cityName: String) : Response<ForecastResponse>

}