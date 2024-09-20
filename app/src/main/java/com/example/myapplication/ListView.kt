package com.example.myapplication

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var frutas:ArrayList<String> = ArrayList()
        frutas.add("Manzana")
        frutas.add("Durazno")
        frutas.add("Platano")
        frutas.add("Sandía")

        val lista = findViewById<ListView>(R.id.lista)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, frutas)
        lista.adapter = adaptador

        lista.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->

            Toast.makeText(this, "Posicion:" + position + "- fruta: "
                    + frutas.get(position) + ", id: " + id + ", itemPosition: "+ parent.getItemAtPosition(position) +
                    " - view: " + parent.selectedView
                , Toast.LENGTH_SHORT).show()


        }


    }
}