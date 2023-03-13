package com.alalmiyaalhura.forecastapp.util

import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

class MyDateTime {




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

}