package com.alalmiyaalhura.forecastapp.ui.data_error_dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.data.source.local.PreferencesManager
import com.alalmiyaalhura.forecastapp.ui.get_daily_forecast.DailyForecastFragment
import com.alalmiyaalhura.forecastapp.ui.util.FragmentUtil
import com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class DataErrorDialogFragment  : DialogFragment() {

    private val TAG = "DataErrorDialogFragment"

   private val viewModel:ErrorDialogViewModel by viewModels()



    interface IOnRetryClickListener{
        fun onRetryClick()
    }

    private var dialogMessage:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val errorMessage = arguments?.getString(ScreensNavigator.DIALOG_MESSAGE)
        dialogMessage = getString(R.string.dialog_message) + "\n" + errorMessage
    }





    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(dialogMessage)
            .setNegativeButton(getString(R.string.cancel), null)
            .setPositiveButton(
                getString(R.string.retry)
            ) { dialog, which ->

                lifecycleScope.launchWhenStarted {
                    val cityName = viewModel.getCityName()

                    ScreensNavigator
                        .navigateFromErrorDialogToDailyForecastPage(cityName, findNavController())

                    Log.i(TAG, "onCreateDialog: cityName = $cityName")
                }
//                retryLoadData()
                dialog.dismiss()
            }.create()



    private fun retryLoadData(){
        try {

            val currentDestination = FragmentUtil.getCurrentDestination(activity)

            val dailyForecastFragment:DailyForecastFragment? =  currentDestination as? DailyForecastFragment

            if (dailyForecastFragment != null) {
                val iOnRetryClickListener = (dailyForecastFragment) as IOnRetryClickListener
                iOnRetryClickListener.onRetryClick()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }





}