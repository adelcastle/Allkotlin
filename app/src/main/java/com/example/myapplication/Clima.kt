package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Clases.Ciudad
import com.example.myapplication.Clases.vNetwork
import com.google.gson.Gson

class Clima : AppCompatActivity() {
    lateinit var tvCiudad: TextView
    lateinit var tvTemperatura: TextView
    lateinit var tvClima: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clima)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        tvCiudad = findViewById(R.id.tvTituloClima)
        tvTemperatura = findViewById(R.id.tvGrados)
        tvClima = findViewById(R.id.tvEstado)

        val ciudad = intent.getStringExtra("Ciudad")

        if(vNetwork.hayRed(this)){

                solicitudVolley("https://api.openweathermap.org/data/2.5/weather?id=$ciudad&APPID=36f7c96678a25cd7bbd4c468f7f3b66c&units=metric&lang=es")


        }else{
            Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()
        }




    }


    private fun solicitudVolley(url:String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, {
                response ->
            try {
                Log.d("SolicitudVolley", response)

                var gson = Gson()
                var ciudad = gson.fromJson(response, Ciudad::class.java)
                Log.d("SolicitudVolley","Name" + ciudad.name  +" Temp:"+ ciudad.main.temp.toString())

                tvCiudad.text = ciudad.name
                tvTemperatura.text = ciudad.main.temp.toString()
                tvClima.text = ciudad.weather[0].description



            }catch (e:Exception){
                Log.d("SolicitudVolley", "Error al procesar solicitud Volley")
            }
        }, {})

        queue.add(solicitud)
    }
}