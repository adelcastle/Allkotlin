@file:Suppress("DEPRECATION")

package com.example.myapplication.Clases
import android.os.AsyncTask
import android.os.StrictMode
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DescargaURL(var completadoListener: CompletadoListener?): AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String? {

        try {
            return descargarDatosAsinc(params[0])
        }catch (e:Exception){
            return null
        }
        return null //descargarDatos(params[0])
    }
    override fun onPostExecute(result: String) {
        try {
            completadoListener?.descargaCompleta(result)
        }catch (e:Exception){


        }
    }

    @Throws(Exception::class)
    fun descargarDatosAsinc(url:String):String{
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





}