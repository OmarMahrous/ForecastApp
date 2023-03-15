package com.alalmiyaalhura.forecastapp.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MyDateTime {


companion object {

    fun currentTimeIn24Format(subtract30Min: Boolean): String {
        val rightNow = Calendar.getInstance()
        if (subtract30Min)
            rightNow.add(Calendar.MINUTE, -30) // Subtract a 30 minutes from current time

        val currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY)
//            val currentHourIn12Format = rightNow.get(Calendar.HOUR)
        val currentMin = rightNow.get(Calendar.MINUTE)

//            println("currentHourIn24Format: $currentHourIn24Format")
//            println("currentHourIn12Format: $currentHourIn12Format")


        return "${convertDate(currentHourIn24Format)}:$currentMin"
    }

    private fun convertDate(input: Int): String? {
        return if (input >= 10) {
            input.toString()
        } else {
            "0$input"
        }
    }

    fun getDate(dateTime: String): String {
        try {


            val stringArray = dateTime.split(" ") // 2023-03-13 18:00:00
            val date = stringArray[0]

            return date
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun getTodayDateTime(): String {
//        val time = Calendar.getInstance().time
        val formatter = dateTimeFormatter()
        val date = Date()
        val current = formatter.format(date)

        return current
    }

    private fun dateTimeFormatter(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    fun getDayOfWeek(dateTime:String): String {
        try {
            val dayOfWeekFormatter = SimpleDateFormat("EEEE")
            val date = dateTimeFormatter().parse(dateTime);
            val dayOfTheWeek = dayOfWeekFormatter.format(date)
            return dayOfTheWeek
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    /**
     * Get current time in 12 hour format with AM/PM
     */
    fun getHour(dateTime:String):String{
        try {


            val hourDateFormat: DateFormat = SimpleDateFormat("hh a")
            val date = dateTimeFormatter().parse(dateTime);
            val hour = hourDateFormat.format(date)
            return hour
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }
}
}