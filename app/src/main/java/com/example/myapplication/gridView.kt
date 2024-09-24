package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class gridView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_view)
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

        var grid:GridView = findViewById(R.id.grid)
         val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, frutas)
            grid.adapter = adaptador

        grid.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, frutas.get(i), Toast.LENGTH_SHORT).show()
        }

    }
}