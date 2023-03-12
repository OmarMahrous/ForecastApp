package com.alalmiyaalhura.forecastapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast_table")
data class Forecast(
    @SerializedName("dt")
    @Expose
     val dt:Int? = null,

@SerializedName("main")
@Expose
@Embedded
 val main:CityMainData? = null,

@SerializedName("weather")
@Expose
 val weatherList:List<Weather>?,

@SerializedName("clouds")
@Expose
@Embedded
 val clouds:Clouds? = null,

@SerializedName("wind")
@Expose
@Embedded
 val wind:Wind? = null,

@SerializedName("visibility")
@Expose
 val visibility:Int? = null,

@SerializedName("pop")
@Expose
 val pop:Int? = null,

@SerializedName("sys")
@Expose
@Embedded
 val sys:Sys? = null,

@SerializedName("dt_txt")
@Expose
 val dtTxt:String? = null,

@PrimaryKey(autoGenerate = true) var id: Int? = null
)
