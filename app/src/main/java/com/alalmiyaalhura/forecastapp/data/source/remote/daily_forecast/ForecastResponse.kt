package com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast

import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.source.remote.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForecastResponse : BaseResponse() {

    @SerializedName("list")
    @Expose
    val list: List<Forecast>? = null

}