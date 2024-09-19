package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast


fun redDisponible(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (connectivityManager != null) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
    }
    return false
}



class MainActivity : AppCompatActivity() {

    lateinit var btAPI: Button
    lateinit var btClima: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        controles()
        eventos()


    }

    private fun controles() {
        btAPI = findViewById(R.id.btAPI)
        btClima = findViewById(R.id.btClima)

    }

    private fun eventos() {
        btAPI.setOnClickListener {
            if(redDisponible(this) ==true){

                intent = Intent(this, WebApi::class.java)
                intent.putExtra("Valor","Pruebas Endpoints-" + LocalDate.now() )
                startActivity(intent)
                
                Toast.makeText(this,"Hay Red", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()
            }

        }

        btClima.setOnClickListener {
            intent = Intent(this, Ciudades::class.java)
            intent.putExtra("Valor","Explorar Clima-" + LocalDate.now() )
            startActivity(intent)
        }

    }


}