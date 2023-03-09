package com.alalmiyaalhura.forecastapp.ui.search_city

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.databinding.FragmentSearchCityBinding
import com.alalmiyaalhura.forecastapp.ui.util.ActionbarUtil
import com.alalmiyaalhura.forecastapp.ui.util.MessageLogger
import com.alalmiyaalhura.forecastapp.ui.util.ScreensNavigator


class SearchCityFragment : Fragment(R.layout.fragment_search_city) {

    private val TAG = "SearchCityFragment"



    private var _binding: FragmentSearchCityBinding? =null
    private val binding: FragmentSearchCityBinding get() =_binding!!

//    private var listAdapter:CompetitionsAdapter?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentSearchCityBinding.inflate(inflater, container, false)

        initUiViews()

        getSearchInputFromUser()


        return binding.root
    }

    private fun initUiViews() {
        binding.goToDailyForecastFab.setOnClickListener {
            val cityName = binding.cityEditText.text?.trim().toString()
            if (cityName.isEmpty())
                MessageLogger.shortToast(requireContext(), getString(R.string.city_invalid))
            else
                ScreensNavigator.navigateToDailyForecastPage(cityName, findNavController())
        }
    }

    private fun getSearchInputFromUser() {
        binding.cityEditText.addTextChangedListener(textInputFieldWatcher)
    }

    private val textInputFieldWatcher = object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


        }

        override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding.actionBarInclude.tvTitle.text = input.toString()

            /* TODO  hit api and get daily forecast
            data for given city name, and cache it. */
        }

        override fun afterTextChanged(p0: Editable?) {
        }

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
        binding.cityEditText.removeTextChangedListener(textInputFieldWatcher)
        _binding = null
    }

}