package com.example.myapplication

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Clases.AdaptadorPersonalGridView
import com.example.myapplication.Clases.Frutagv

class gridViewPersonal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_view_personal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var frutas = ArrayList<Frutagv>()
        frutas.add(Frutagv("Manzana", R.drawable.manzana))
        frutas.add(Frutagv("Platano", R.drawable.platano))
        frutas.add(Frutagv("durazno", R.drawable.durazno))
        frutas.add(Frutagv("Sandía", R.drawable.sandia))

        var grid = findViewById<GridView>(R.id.gridViewPersonal)
        //val adaptador = AdaptadorPersonalGridView(this, frutas)
            grid.adapter = AdaptadorPersonalGridView(this, frutas)

        grid.setOnItemClickListener { adapterView, view, i, l ->
            println(i)
            makeText(this, frutas[i].nombre,LENGTH_SHORT).show()
            println(frutas.get(i).nombre)
            println(frutas.get(i).imagen)

        }
         /*
        grid.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->

            println(position)
            println(frutas.get(position).imagen)
            makeText(this, frutas[position].nombre,LENGTH_SHORT).show()
            println(frutas.get(position).imagen)

        }*/
    }
}