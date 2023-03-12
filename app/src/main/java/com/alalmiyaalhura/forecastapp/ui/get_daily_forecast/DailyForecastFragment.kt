package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.data.source.local.database.ForecastDao
import com.alalmiyaalhura.forecastapp.data.source.remote.daily_forecast.ForecastApi
import com.alalmiyaalhura.forecastapp.databinding.FragmentDailyForecastBinding
import com.alalmiyaalhura.forecastapp.ui.util.ActionbarUtil
import com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator
import com.alalmiyaalhura.forecastapp.util.NetworkHelper
import javax.inject.Inject

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

    }

    private fun getCityName(): String {
        return arguments?.getString(ScreensNavigator.CITY_NAME) ?: ""
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    private fun updateUiListComponent() {
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