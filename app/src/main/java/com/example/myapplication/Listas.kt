package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate

class Listas : AppCompatActivity() {

    lateinit var btListView: Button
    lateinit var btListViewPersonal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        controles()
        eventos()
    }

   fun controles(){
        btListView = findViewById(R.id.btListView)
        btListViewPersonal = findViewById(R.id.btListViewPersonal)
    }

    fun eventos(){
        btListView.setOnClickListener {
            intent = Intent(this, ListView::class.java)
            intent.putExtra("Valor","Listas-" + LocalDate.now() )
            startActivity(intent)
        }

        btListViewPersonal.setOnClickListener {
            intent = Intent(this, ListViewPersonal::class.java)
            intent.putExtra("Valor","Listas Personalizadas-" + LocalDate.now() )
            startActivity(intent)
        }
    }


}