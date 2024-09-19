package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ciudades : AppCompatActivity() {

    lateinit var btCiudadMexico: Button
    lateinit var btCiudadBerlin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ciudades)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btCiudadMexico = findViewById(R.id.btCiudadMexico)
        btCiudadBerlin= findViewById(R.id.btCiudadBerlin)

        btCiudadMexico.setOnClickListener {
            intent = Intent(this, Clima::class.java)
            intent.putExtra("Ciudad","Ciudad de Mexico")
            startActivity(intent)
        }

        btCiudadBerlin.setOnClickListener {
            intent = Intent(this, Clima::class.java)
            intent.putExtra("Ciudad","Ciudad de Berlin")
            startActivity(intent)
        }



    }
}