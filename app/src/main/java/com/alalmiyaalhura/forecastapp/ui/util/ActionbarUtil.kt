package com.alalmiyaalhura.forecastapp.ui.util

import android.app.Activity
import android.content.Context
//import androidx.appcompat.app.ActionBar
import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.alalmiyaalhura.forecastapp.R
import com.alalmiyaalhura.forecastapp.ui.MainActivity

class ActionbarUtil {

    companion object{

        fun setCustomActionBar(activity: Activity){

            val mainActivity = activity as MainActivity

            with(mainActivity.actionBar) {
                this?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
                this?.setCustomView(R.layout.custom_action_bar_layout);
            }

        }

        fun showActionBar(activity:Activity, show:Boolean){
            val actionBar = (activity as AppCompatActivity).supportActionBar

            if (show)
                actionBar?.show()
            else
                actionBar?.hide()
        }
    }
}