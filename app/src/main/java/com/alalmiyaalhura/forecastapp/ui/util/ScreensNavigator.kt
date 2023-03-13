package com.alalmiyaalhura.forecastapp.ui.util

import androidx.navigation.NavController
import com.alalmiyaalhura.forecastapp.ui.data_error_dialog.DataErrorDialogFragmentDirections
import com.alalmiyaalhura.forecastapp.ui.get_daily_forecast.DailyForecastFragmentDirections
import com.alalmiyaalhura.forecastapp.ui.search_city.SearchCityFragmentDirections

class ScreensNavigator {
    companion object{

        const val CITY_NAME  = "com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator.CITY_NAME"
        const val DIALOG_MESSAGE  = "com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator.DIALOG_MESSAGE"

        fun navigateToDailyForecastPage(cityName:String, navController: NavController){
            val action = SearchCityFragmentDirections.actionSearchCityFragmentToDailyForecastFragment(cityName)
            navController.navigate(action)
        }

        fun navigateToDataErrorDialog(dialogMessage:String, navController: NavController){
            try {
                val action = DailyForecastFragmentDirections
                    .actionDailyForecastFragmentToDataErrorDialogFragment(dialogMessage)

                navController.navigate(action)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        fun navigateFromErrorDialogToDailyForecastPage(cityName:String, navController: NavController){
            val action = DataErrorDialogFragmentDirections.actionErrorDialogFragmentToDailyForecastFragment(cityName)
            navController.navigate(action)
        }

    }
}