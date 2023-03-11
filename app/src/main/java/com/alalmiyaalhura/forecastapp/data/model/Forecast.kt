package com.alalmiyaalhura.forecastapp.data.model

import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecast(val dt:Int,
@SerializedName("main")
@Expose
@Embedded
private val main:CityMainData,
    @SerializedName("weather")
@Expose
private val weatherList:List<Weather>,
@SerializedName("clouds")
@Expose
private val clouds:Any,
@SerializedName("wind")
@Expose
private val wind:Any,
@SerializedName("visibility")
@Expose
private val visibility:Int,
@SerializedName("pop")
@Expose
private val pop:Int,
@SerializedName("sys")
@Expose
private val sys:Any,
@SerializedName("dt_txt")
@Expose
private val dtTxt:String
)
