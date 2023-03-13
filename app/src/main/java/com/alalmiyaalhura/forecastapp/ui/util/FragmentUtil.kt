package com.alalmiyaalhura.forecastapp.ui.util

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alalmiyaalhura.forecastapp.R

class FragmentUtil {

    companion object{

        fun getCurrentDestination(activity: Activity?): Fragment? {
            val navHostFragment: Fragment? =
                (activity as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            return navHostFragment?.childFragmentManager?.fragments?.get(0)
        }

    }

}