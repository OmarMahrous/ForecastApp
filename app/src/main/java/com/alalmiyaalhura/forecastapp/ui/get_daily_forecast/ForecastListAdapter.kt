package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.databinding.ForecastListItemBinding

class ForecastListAdapter(val context: Context,
                          val forecastList: List<Forecast>)
    : RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ForecastListItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
//        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    class ForecastViewHolder(binding:ForecastListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}


