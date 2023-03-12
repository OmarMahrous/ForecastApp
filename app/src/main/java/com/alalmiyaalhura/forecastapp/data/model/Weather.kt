package com.alalmiyaalhura.forecastapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")
    @Expose
     var w_id:Int?=null,
    @SerializedName("main")
@Expose
 var w_main:String?=null,
    @SerializedName("description")
@Expose
 var description:String?=null,
    @SerializedName("icon")
@Expose
 var icon:String?=null
)
