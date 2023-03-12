package com.alalmiyaalhura.forecastapp.di

import android.content.Context
import com.alalmiyaalhura.forecastapp.data.source.remote.api_service.ApiGenerator
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitApiModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = ApiGenerator.initRetrofit()


    @Provides
    @Singleton
    fun provideForecastApi(retrofit: Retrofit): ForecastApi = retrofit.create(ForecastApi::class.java)

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext appContext: Context): NetworkHelper = NetworkHelper(appContext)
}