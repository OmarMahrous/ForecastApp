package com.alalmiyaalhura.forecastapp.ui.data_error_dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alalmiyaalhura.forecastapp.data.source.local.PreferencesManager
import com.alalmiyaalhura.forecastapp.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class ErrorDialogViewModel @Inject constructor(
    /** Note: We need application scope instead of view model scope
     * because if user tapped on OK in the dialog fragment it will dismissed and its viewmodel will be lost from memory
     * and the deletion process will be cancelled also.
     **/
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val preferencesManager: PreferencesManager
) : ViewModel(){

    private val preferencesFlow = preferencesManager.preferencesFlow



    suspend fun getCityName(): String =
       preferencesFlow.first().cityName


}