package com.alalmiyaalhura.forecastapp.data.source.remote.api_service

import com.alalmiyaalhura.forecastapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiGenerator {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()


    fun initRetrofit(): Retrofit {

        return retrofitBuilder.baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(addOkHttpClient())
            .build()

    }

    private fun addOkHttpClient(): OkHttpClient {

        val builder  = OkHttpClient.Builder()

        builder.addInterceptor(authInterceptor())

        builder.networkInterceptors().add(loggingInterceptor())

        return builder.build()
    }

    private fun authInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().url.newBuilder()

            val httpUrlBuilder = requestBuilder.addQueryParameter("appid", BuildConfig.FORECAST_API_KEY);

            val httpUrl = httpUrlBuilder.build()

            chain.proceed(chain.request().newBuilder().url(httpUrl).build())
        }
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    fun <T> createApiService(retrofit: Retrofit, apiClass: Class<T>):T{
        return retrofit.create(apiClass)
    }

}