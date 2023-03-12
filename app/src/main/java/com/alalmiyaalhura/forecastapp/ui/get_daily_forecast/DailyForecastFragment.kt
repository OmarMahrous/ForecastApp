package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastDao
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.data.util.Status
import com.alalmiyaalhura.forecastapp.databinding.FragmentDailyForecastBinding
import com.alalmiyaalhura.forecastapp.ui.util.ActionbarUtil
import com.alalmiyaalhura.forecastapp.ui.util.MySnackbar
import com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator
import com.alalmiyaalhura.forecastapp.util.NetworkHelper
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DailyForecastFragment : Fragment(R.layout.fragment_daily_forecast){

    private val TAG = "DailyForecastFragment"

    private lateinit var viewModel: DailyForecastViewModel

    @Inject
    lateinit var networkHelper: NetworkHelper
    @Inject
    lateinit var forecastApi: ForecastApi
    @Inject
    lateinit var forecastDao: ForecastDao

    private var _binding: FragmentDailyForecastBinding? =null
    private val binding: FragmentDailyForecastBinding get() =_binding!!

//    private var listAdapter:CompetitionsAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentDailyForecastBinding.inflate(inflater, container, false)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {

        val viewModelFactory = DailyForecastViewModel
            .ForecastViewModelFactory(forecastApi, forecastDao,getCityName())

        viewModel = ViewModelProvider(this, viewModelFactory)[DailyForecastViewModel::class.java]

//        Log.d(TAG, "initViewModel: cityName = ${getCityName()}")

    }

    private fun getCityName(): String {
        return arguments?.getString(ScreensNavigator.CITY_NAME) ?: ""
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchCompetitionDetails()


        checkInternetConnection()

    }

    private fun checkInternetConnection() {
        if (networkHelper.isNetworkConnected())
            getForecasts()
        else {
            getForecastsFromLocal()
        }
    }

    private fun fetchCompetitionDetails(){
        viewModel.fetchData()
    }

    private fun getForecasts() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getForecasts().collect { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        val data = resource.data

                        updateUiListComponent(data)


                        Log.d(TAG, "getForecasts: " +
                                "forecast list size = ${data?.size}")
                    }
                    Status.ERROR ->{

                        val snackbar = MySnackbar()
                        snackbar.showMessage(binding.root, resource.message?:"Unknown error")

                    }

                    else -> { // TODO show loading progress }
                    }
                }
            }

        }
    }

    private fun getForecastsFromLocal(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getForecastsFromLocal().collect { list ->

                    updateUiListComponent(list)

            }
        }
    }



    private fun updateUiListComponent(forecastList: List<Forecast?>?) {
//        binding.competitionsRecyclerView.apply {
//            if (adapter==null)
//                adapter = listAdapter
//
//            listAdapter?.submitList(competitionList)
//
//        }
    }

    override fun onResume() {
        super.onResume()
        ActionbarUtil.showActionBar(requireActivity(), false)
    }

    override fun onStop() {
        super.onStop()
        ActionbarUtil.showActionBar(requireActivity(), true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}