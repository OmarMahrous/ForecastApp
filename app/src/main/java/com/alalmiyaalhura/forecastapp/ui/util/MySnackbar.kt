package com.alalmiyaalhura.forecastapp.ui.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.time.Duration

class MySnackbar() :IMessage {


    private var snackbarDuration= Snackbar.LENGTH_SHORT

    public fun setDuration(duration: Int){
                snackbarDuration = duration
    }

    fun getDuration():Int{
        return snackbarDuration
    }


    override fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, getDuration()).show()
    }
}