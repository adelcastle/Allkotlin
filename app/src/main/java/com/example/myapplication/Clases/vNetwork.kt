package com.example.myapplication.Clases

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.net.ConnectivityManager


class vNetwork {
    //Companion object permite acceder a la funcion sin instanciar la clase
    companion object {
        fun hayRed(activity:AppCompatActivity):Boolean{
            val connectiviyManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectiviyManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected


        }

    }


}