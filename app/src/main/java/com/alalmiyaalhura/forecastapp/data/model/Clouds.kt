package com.alalmiyaalhura.forecastapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    @Expose
    private val all:Int
)
