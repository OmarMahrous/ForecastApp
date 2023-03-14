package com.alalmiyaalhura.forecastapp.data.util

import com.alalmiyaalhura.forecastapp.data.model.Forecast

class ListGenerator {

    companion object{

        fun createRandomForecastList():ArrayList<Forecast>{
            val forecastList = arrayListOf<Forecast>()
            forecastList.add(Forecast(0,null, id = 1, weatherList = listOf(), dtTxt = "Today"))
            forecastList.add(Forecast(0,null, id = 2, weatherList = listOf(), dtTxt = "Yesterday"))
            forecastList.add(Forecast(0,null, id = 3, weatherList = listOf(), dtTxt = "Tomorrow"))
            forecastList.add(Forecast(0,null, id = 4, weatherList = listOf(), dtTxt = "18/03/2023"))

            return forecastList
        }

        fun createForecastListWithDuplication(): List<Forecast?>? {
            val forecastList = arrayListOf<Forecast>()
            forecastList.add(Forecast(0,null, id = 1, weatherList = listOf(), dtTxt = "Today"))
            forecastList.add(Forecast(0,null, id = 1, weatherList = listOf(), dtTxt = "Today"))
            forecastList.add(Forecast(0,null, id = 2, weatherList = listOf(), dtTxt = "Tomorrow"))

            return forecastList
        }

    }

}