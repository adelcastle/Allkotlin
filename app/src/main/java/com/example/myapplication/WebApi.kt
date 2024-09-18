package com.example.myapplication

import android.app.DownloadManager
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Clases.CompletadoListener
import com.example.myapplication.Clases.vNetwork
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import com.example.myapplication.Clases.DescargaURL
import okhttp3.OkHttpClient


class WebApi : AppCompatActivity(), CompletadoListener {

    lateinit var tvTituloWebApi: TextView
    lateinit var btValRed: Button
    lateinit var btEndNativo: Button
    lateinit var btEndVolley: Button
    lateinit var btEndHttpAsinc: Button
    lateinit var btEndOkHttp: Button

    override fun descargaCompleta(resultado: String) {
        Log.d( "HttpAsinc", resultado)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_api)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTituloWebApi = findViewById(R.id.tvTituloWebApi)
        btValRed=findViewById(R.id.btValRed1)
        btEndNativo=findViewById(R.id.btEndNativo)
        btEndVolley=findViewById(R.id.btEndVolley)
        btEndHttpAsinc=findViewById(R.id.btEndHttpAsinc)
        btEndOkHttp=findViewById(R.id.btEndOkHttp)

        tvTituloWebApi.text = intent.getStringExtra("Valor")

        eventos()


    }
    @Suppress("DEPRECATION")
    fun eventos(){

        btValRed.setOnClickListener {

         if(vNetwork.hayRed(this) ==true){
             Toast.makeText(this,"Hay Red", Toast.LENGTH_SHORT).show()
         }else{
             Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()
         }

           // Toast.makeText(this,"Click en Validar Red", Toast.LENGTH_SHORT).show()
        }

        btEndNativo.setOnClickListener {
            if(vNetwork.hayRed(this) ==true){
             Log.d( "HttpNativo", "Antes de enviar Solicitud")
             Log.d( "HttpNativo", descargarDatos("https://www.google.com"))
                Log.d( "HttpNativo", "despues de procesar Solicitud")
            }else {


                Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()}
            }


        btEndHttpAsinc.setOnClickListener {
            if(vNetwork.hayRed(this) ==true){
                Log.d( "HttpAsinc", "Antes de enviar Solicitud")
                DescargaURL(this).execute("https://www.google.com")
               Log.d( "HttpAsinc", "despues de procesar Solicitud")

            }else {

                Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()}
        }

        btEndVolley.setOnClickListener {
            if(vNetwork.hayRed(this) ==true){
                println("Antes de enviar Solicitud Volley")
                solicitudVolley("https://www.google.com")
                println("despues de procesar Solicitud Volley")
            }else{
                Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()
            }

        }

        btEndOkHttp.setOnClickListener {
            if(vNetwork.hayRed(this) ==true){
                println("Antes de enviar Solicitud okHttp")
                solicituOkHttp("https://www.google.com")
                println("despues de procesar Solicitud okHttp")
            }else{
                Toast.makeText(this,"Red no disponible", Toast.LENGTH_SHORT).show()
            }

        }
    }


    @Throws(Exception::class)
    fun descargarDatos(url:String):String{
            //Permite ejecutar codigo en el hilo principal
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)


            var inputStream: InputStream? = null
           try {
               val url1 = URL(url)
               val conn = url1.openConnection() as HttpsURLConnection
               conn.requestMethod = "GET"
               conn.connect()
               inputStream= conn.inputStream
               return inputStream.bufferedReader().use {
                   it.readText()
               }

           }finally {
               if (inputStream != null){
                   inputStream.close()
               }
           }
        }

    //metodo volley
    private fun solicitudVolley(url:String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, {
                response ->
                try {
                    Log.d("SolicitudVolley", response)
                }catch (e:Exception){
                    Log.d("SolicitudVolley", "Error al procesar solicitud Volley")
                }
            }, {})

        queue.add(solicitud)
    }


    //metodo okhttp
    private fun solicituOkHttp(url: String){
        val cliente = OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()
        cliente.newCall(solicitud).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                Log.d("SolicitudOkHttp", "Error al procesar solicitud OkHttp")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
             println("OkHttp devuelve respuesta")
               val result = response.body?.string()
                println("result:$result")
                //Devuelve al thead principal
                this@WebApi.runOnUiThread {
                    try {
                        Log.d("SolicitudOkHttp", result!!)
                    }catch(e:Exception){
                        Log.d("SolicitudOkHttp", "Error al procesar solicitud OkHttp")
                    }

                }
            }
        })


    }
}