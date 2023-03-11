package com.alalmiyaalhura.forecastapp.ui.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

class MySnackbar() :IMessage {

    override fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}