package com.alalmiyaalhura.forecastapp.ui.util

import androidx.navigation.NavController
import com.alalmiyaalhura.forecastapp.ui.search_city.SearchCityFragmentDirections

class ScreensNavigator {
    companion object{

        const val CITY_NAME  = "com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator.CITY_NAME"

        fun navigateToDailyForecastPage(cityName:String, navController: NavController){
            val action = SearchCityFragmentDirections.actionSearchCityFragmentToDailyForecastFragment(cityName)
            navController.navigate(action)
        }

    }
}