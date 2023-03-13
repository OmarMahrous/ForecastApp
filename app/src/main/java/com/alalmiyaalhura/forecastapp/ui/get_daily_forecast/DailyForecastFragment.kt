package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastDao
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.data.util.Status
import com.alalmiyaalhura.forecastapp.databinding.FragmentDailyForecastBinding
import com.alalmiyaalhura.forecastapp.ui.data_error_dialog.DataErrorDialogFragment
import com.alalmiyaalhura.forecastapp.ui.util.ActionbarUtil
import com.alalmiyaalhura.forecastapp.ui.util.FragmentUtil
import com.alalmiyaalhura.forecastapp.ui.util.MySnackbar
import com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator
import com.alalmiyaalhura.forecastapp.util.MyDateTime
import com.alalmiyaalhura.forecastapp.util.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_daily_forecast.*
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

    private var listAdapter:ForecastListAdapter?=null

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

        fetchForecasts()


        checkInternetConnection()

        Log.d(TAG, "onViewCreated: ")

    }

    private fun checkInternetConnection() {

        if (networkHelper.isNetworkConnected()) {
            binding.notAccurateData.visibility = View.GONE
            getForecasts()
        }else {
            binding.notAccurateData.visibility = View.VISIBLE
            getForecastsFromLocal()
        }
    }

    private fun fetchForecasts(){
        viewModel.fetchData()
    }

    private fun getForecasts() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getForecasts().collect { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        showLoadingLayout(false)

                        val data = resource.data ?: listOf()

                        val todayForecast = viewModel.getTodayForecast(data)
                        todayForecast?.let { updateUiViews(it) }

                        updateUiListComponent(data)

                        Log.d(TAG, "getForecasts: " +
                                "forecast list size = ${data?.size}")
                    }
                    Status.ERROR ->{
                        showLoadingLayout(false)

                        val errorMessage = resource.message?:"Unknown error"

                        if (isAdded && isVisible)
                            ScreensNavigator.navigateToDataErrorDialog(errorMessage, findNavController())


//                        val snackbar = MySnackbar()
//                        snackbar.showMessage(binding.root, errorMessage)

                    }

                    else -> {
                        showLoadingLayout(true)
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

    private fun showLoadingLayout(isLoadingData:Boolean){
        if (isLoadingData){
            binding.forecastDataLlayout.visibility = View.GONE
            binding.loadingLayoutInclude.root.visibility = View.VISIBLE
        }else{
            binding.forecastDataLlayout.visibility = View.VISIBLE
            binding.loadingLayoutInclude.root.visibility = View.GONE
        }
    }

    private fun updateUiViews(forecast: Forecast){
        with(binding){
            with(forecast){
                actionBarInclude.tvTitle.text = getCityName()
                weatherMain.text = weatherList?.get(0)?.w_main
                city_temperature.text = ""+main?.temp

                val forecastDate = dtTxt?.let { MyDateTime.getDayOfWeek(it) }
                dateTextView.text = forecastDate

                maxTempTextView.text = ""+main?.tempMax
                minTempTextView.text = ""+main?.tempMin
            }
        }
    }

    private fun updateUiListComponent(forecastList: List<Forecast?>) {
        listAdapter = ForecastListAdapter(requireContext(), forecastList)

        binding.weekForecastsRecyclerview.apply {
            if (adapter==null)
                adapter = listAdapter

        }
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