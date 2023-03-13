package com.alalmiyaalhura.forecastapp.data.source.local.database.type_converter

import androidx.room.TypeConverter
import com.alalmiyaalhura.forecastapp.data.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MyTypeConverter {

    @TypeConverter
    fun fromWeatherListToJSON(weatherList: List<Weather>): String {

        val listType: Type = object : TypeToken<List<Weather>>() {}.type



        return Gson().toJson(weatherList, listType)
    }

    @TypeConverter
    fun fromJSONToWeatherList(json: String): List<Weather> {
        val listType: Type = object : TypeToken<List<Weather>>() {}.type

        return Gson().fromJson(json,listType)
    }

}