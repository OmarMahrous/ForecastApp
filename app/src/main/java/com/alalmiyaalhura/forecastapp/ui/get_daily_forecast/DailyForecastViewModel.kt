package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.repository.forecast.ForecastRepository
import com.alalmiyaalhura.forecastapp.data.repository.forecast.ForecastRepositoryImpl
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastDao
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class DailyForecastViewModel constructor(
    forecastApi: ForecastApi,
    forecastDao: ForecastDao,
    cityName:String
)  : ViewModel() {

    private val forecastRepository:ForecastRepository

    init {
        forecastRepository = ForecastRepositoryImpl
            .create(forecastApi, forecastDao, cityName, viewModelScope)
    }

    fun getForecasts(): Flow<Resource<List<Forecast?>>> {
        return forecastRepository.getForecasts()
    }

    fun getForecastsFromLocal(): Flow<List<Forecast>> {

        return forecastRepository.getForecastsFromLocal()

    }



    fun fetchData(){
        viewModelScope.launch(Dispatchers.IO) {
            forecastRepository.fetchData()
        }
    }


    internal class ForecastViewModelFactory(
        private val forecastApi: ForecastApi,
        private val forecastDao: ForecastDao,
        private val cityName:String
    )
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DailyForecastViewModel::class.java)){
                DailyForecastViewModel( forecastApi, forecastDao, cityName)  as T
            }else
                throw IllegalArgumentException("ViewModel not found")
        }
    }

}