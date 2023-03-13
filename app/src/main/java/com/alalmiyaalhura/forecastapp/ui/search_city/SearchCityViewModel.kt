package com.alalmiyaalhura.forecastapp.ui.search_city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alalmiyaalhura.forecastapp.data.source.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel(){



    fun setCityName(cityName:String) = viewModelScope.launch {
        preferencesManager.setCityName(cityName)
    }


}