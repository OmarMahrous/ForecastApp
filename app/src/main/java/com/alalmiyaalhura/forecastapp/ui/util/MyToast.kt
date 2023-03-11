package com.alalmiyaalhura.forecastapp.ui.util

import android.view.View
import android.widget.Toast

class MyToast() : IMessage{

    override fun showMessage(view: View, message: String) {
        Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
    }

}