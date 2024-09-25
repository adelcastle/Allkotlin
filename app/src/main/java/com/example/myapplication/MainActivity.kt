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
import androidx.lifecycle.Lifecycle


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
    lateinit var btListas: Button
    lateinit var btAppEvent: Button
    lateinit var btToolBar: Button
    var oncreate:String = "Iniciando"



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
       //  oncreate = "var Antes de EventoClic"
       // Toast.makeText(this, oncreate, Toast.LENGTH_SHORT).show()
        btAppEvent.setOnClickListener {
            oncreate="var despues Evento Click"
            Toast.makeText(this, oncreate, Toast.LENGTH_SHORT).show()

        }


    }

    private fun controles() {
        btAPI = findViewById(R.id.btAPI)
        btClima = findViewById(R.id.btClima)
        btListas = findViewById(R.id.btLista)
        btAppEvent = findViewById(R.id.btAppEvent)
        btToolBar = findViewById(R.id.btToolBar)

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
        btListas.setOnClickListener {
            intent = Intent(this, Listas::class.java)
            intent.putExtra("Valor","Listas-" + LocalDate.now() )
            startActivity(intent)
        }

        btToolBar.setOnClickListener {
            intent = Intent(this, activityToolBar::class.java)
            intent.putExtra("Valor","Toolbar-" + LocalDate.now() )
            startActivity(intent)
        }




    }
    // Eventos de ciclo de vida y mantener valor variables al cambiar de pantalla

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("stado",oncreate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        oncreate = savedInstanceState.getString("stado").toString()
        Toast.makeText(this, oncreate, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "En Transición - onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Aplicativo oculto - OnStop", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Aplicativo visible - onResume", Toast.LENGTH_SHORT).show()
    }


    //Ondestroyed se usa cuando android destruye la actividad
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Aplicativo cerrado - onDestroy", Toast.LENGTH_SHORT).show()
    }



}