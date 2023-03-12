package com.alalmiyaalhura.forecastapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    @Expose
    private val pod:String
)
