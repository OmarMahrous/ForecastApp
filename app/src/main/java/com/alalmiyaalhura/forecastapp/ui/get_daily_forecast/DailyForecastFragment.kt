package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.databinding.FragmentDailyForecastBinding
import com.alalmiyaalhura.forecastapp.ui.util.ActionbarUtil

class DailyForecastFragment : Fragment(R.layout.fragment_daily_forecast){

    private val TAG = "DailyForecastFragment"



    private var _binding: FragmentDailyForecastBinding? =null
    private val binding: FragmentDailyForecastBinding get() =_binding!!

//    private var listAdapter:CompetitionsAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentDailyForecastBinding.inflate(inflater, container, false)



        return binding.root
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