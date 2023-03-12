package com.alalmiyaalhura.forecastapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast_table")
data class Forecast(
    @SerializedName("dt")
    @Expose
    private val dt:Int,

@SerializedName("main")
@Expose
@Embedded
private val main:CityMainData,

@SerializedName("weather")
@Expose
private val weatherList:List<Weather>,

@SerializedName("clouds")
@Expose
@Embedded
private val clouds:Clouds,

@SerializedName("wind")
@Expose
@Embedded
private val wind:Wind,

@SerializedName("visibility")
@Expose
private val visibility:Int,

@SerializedName("pop")
@Expose
private val pop:Int,

@SerializedName("sys")
@Expose
@Embedded
private val sys:Sys,

@SerializedName("dt_txt")
@Expose
private val dtTxt:String,

@PrimaryKey(autoGenerate = true) val id: Int
)
