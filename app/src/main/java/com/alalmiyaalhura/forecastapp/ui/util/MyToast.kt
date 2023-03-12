package com.alalmiyaalhura.forecastapp.ui.util

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MyToast() : IMessage{

    private var toastDuration= Toast.LENGTH_SHORT

    fun setDuration(duration: Int){
        toastDuration = duration
    }

    fun getDuration():Int{
        return toastDuration
    }

    override fun showMessage(view: View, message: String) {
        Toast.makeText(view.context, message, getDuration()).show()
    }

}