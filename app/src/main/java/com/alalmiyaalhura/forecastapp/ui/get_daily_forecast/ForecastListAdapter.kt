package com.alalmiyaalhura.forecastapp.ui.get_daily_forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.data.model.Forecast
import com.alalmiyaalhura.forecastapp.databinding.DayForecastListItemBinding
import com.alalmiyaalhura.forecastapp.util.MyDateTime

class ForecastListAdapter(val context: Context,
                          val forecastList: List<Forecast?>)
    : RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = DayForecastListItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val currentForecast = forecastList[position]


        currentForecast?.let {

            if (isTodayForecast(currentForecast))
                holder.binding.dateTextView.text = it.dtTxt?.let { it1 -> MyDateTime.getHour(it1) }
            else if (isForecastAfterCurrentDate(it)){
                holder.binding.dateTextView.text = getForecastDayDate(it.dtTxt)

            } else
                holder.binding.dateTextView.text = it.dtTxt?.let { MyDateTime.getDate(it) }

            val weatherMain = it.weatherList?.get(0)?.w_main ?: ""
            holder.binding.weatherImageView.setImageDrawable(context.getDrawable(getWeatherIcon(weatherMain)))

            holder.bind(it)

        }

    }

    /**
     * Check the next forecast after current date (Maximum 6 days)
     */
    private fun isForecastAfterCurrentDate(forecast: Forecast): Boolean {

        val diffBetweenDates = forecast.dtTxt?.let { it1 -> MyDateTime.getDate(it1) }?.compareTo(getTodayDate())!!

        return diffBetweenDates in 1..6
    }

    private fun isTodayForecast(forecast: Forecast): Boolean {
        return forecast.dtTxt?.contains(getTodayDate()) == true
    }

    private fun getTodayDate():String{
        val todayDateTime = MyDateTime.getTodayDateTime()

        return MyDateTime.getDate(todayDateTime)
    }


    override fun getItemCount(): Int {
        return forecastList.size
    }

    private fun getForecastDayDate(dtTxt: String?): String {

        return dtTxt?.let { MyDateTime.getDayOfWeek(it) } ?: ""
    }


    private fun getWeatherIcon(weatherMain:String):Int{
        return when(weatherMain){
            "Clouds" ->{R.drawable.ic_weather_cloudy}
            "Rain" ->{R.drawable.ic_weather_rainy_day}
            else ->{R.drawable.ic_weather_sunny}
        }
    }

    class ForecastViewHolder( val binding:DayForecastListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentForecast: Forecast?) {
            with(binding){
                forecast = currentForecast
                executePendingBindings()
            }

        }


    }
}


