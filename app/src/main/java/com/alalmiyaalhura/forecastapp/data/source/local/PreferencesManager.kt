package com.alalmiyaalhura.forecastapp.data.source.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = "PreferencesManager"
private val CLASS_NAME = "com.alalmiyaalhura.forecastapp.data.source.local.PreferencesManager"


data class ForecastPreferences(val cityName:String)

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val datastore = context.createDataStore("forecast_preferences")

    val preferencesFlow = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences: ", exception)
                emit(emptyPreferences())
            } else
                throw exception

        }
        .map { preferences ->

            val cityName = preferences[PreferencesKeys.cityName] ?: ""
            ForecastPreferences(cityName)
        }

    suspend fun setCityName(cityName: String) {
        datastore.edit { preferences ->

            preferences[PreferencesKeys.cityName] = cityName
        }
    }

    private object PreferencesKeys {
        val cityName = preferencesKey<String>(CLASS_NAME+"cityName")
    }

}