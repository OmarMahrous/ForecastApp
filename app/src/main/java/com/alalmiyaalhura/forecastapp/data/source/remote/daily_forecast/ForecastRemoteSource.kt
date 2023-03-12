package com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast

import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.util.DataSource
import com.alalmiyaalhura.forecastapp.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class ForecastRemoteSource(val forecastApi: ForecastApi) : DataSource<Resource<List<Forecast>>>{

    private val TAG = "ForecastRemoteSource"

    private val mDataApi: MutableStateFlow<Resource<List<Forecast>>> = MutableStateFlow(Resource.loading())

    override fun getData(): Flow<Resource<List<Forecast>>> {
        return mDataApi

    }

    override fun onError(): Flow<String?> {
        return flowOf(mDataApi.value.message)
    }

    suspend fun fetch(cityName:String){
        try {
            val response = forecastApi.getDailyForecast(cityName)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val forecastResponse = response.body()
                    val count = forecastResponse?.cnt
                    val code = forecastResponse?.cod ?:""

                    if (code == "200"){
                        forecastResponse?.list?.let {
                            mDataApi.value = Resource.success(it)

                        }
                    }else
                        mDataApi.value = Resource.error(response.message())
                }else
                    mDataApi.value = Resource.error(response.message())


            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }




}