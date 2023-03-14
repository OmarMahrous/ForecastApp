package com.alalmiyaalhura.forecastapp.data.util

import com.alalmiyaalhura.forecastapp.data.model.Forecast

class ListGenerator {

    companion object{

        fun createRandomForecastList():ArrayList<Forecast>{
            val forecastList = arrayListOf<Forecast>()
            forecastList.add(Forecast(0,null, id = 1, weatherList = null, dtTxt = "Today"))
            forecastList.add(Forecast(0,null, id = 2, weatherList = null, dtTxt = "Yesterday"))
            forecastList.add(Forecast(0,null, id = 3, weatherList = null, dtTxt = "Tomorrow"))
            forecastList.add(Forecast(0,null, id = 4, weatherList = null, dtTxt = "18/03/2023"))

            return forecastList
        }

    }

}