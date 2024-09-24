package com.example.myapplication

import android.media.Image
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Clases.AdaptadorPersonal
import android.widget.ListView

class ListViewPersonal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_list_view_personal)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var frutas = ArrayList<Fruta>()
        frutas.add(Fruta("Manzana", R.drawable.manzana))
        frutas.add(Fruta("Platano", R.drawable.platano))
        frutas.add(Fruta("durazno", R.drawable.durazno))
        frutas.add(Fruta("Sandía", R.drawable.sandia))


        var lista = findViewById<ListView>(R.id.listPersonal)
        val adaptador = AdaptadorPersonal(this, frutas)
        lista.adapter = adaptador

    }


}

class  Fruta ( nombre: String,  imagen: Int){

    var nombre: String = nombre
    var imagen:Int = imagen
init {
    this.nombre = nombre
    this.imagen = imagen

}
}