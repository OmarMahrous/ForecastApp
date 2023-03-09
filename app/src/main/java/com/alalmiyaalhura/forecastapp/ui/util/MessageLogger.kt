package com.alalmiyaalhura.forecastapp.ui.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Create Toast or Snackbar
 */
class MessageLogger {

    companion object{


        fun shortToast(context: Context, msg:String){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun longToast(context: Context, msg:String){
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun shortSnackbar(view: View, msg:String){
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
        }

        fun longSnackbar(view: View, msg:String){
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
        }
    }

}