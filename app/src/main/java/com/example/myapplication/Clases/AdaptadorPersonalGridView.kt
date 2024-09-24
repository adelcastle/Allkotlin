package com.example.myapplication.Clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R

class AdaptadorPersonalGridView(var contexto: Context, items:ArrayList<Frutagv>):BaseAdapter() {


    var frutas:ArrayList<Frutagv>? = null
    override fun getCount(): Int {
       return frutas!!.count()
    }

    override fun getItem(p0: Int): Any {
        //return frutas!!.get(p0)
        return frutas!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista =  p1
        var holder:ViewHolder? = null
        if(vista == null){
            vista = LayoutInflater.from(contexto).inflate(R.layout.templategridview, null)
            holder = ViewHolder(vista)
            vista.tag = holder
        }
        else{
            holder=vista.tag as? ViewHolder
        }
        var item = frutas!![p0]
        holder?.nombre?.text = item.nombre
        holder?.imagen?.setImageResource(item.imagen)
        return vista!!
    }

    private class ViewHolder(vista:View){
        var nombre: TextView? = null
        var imagen: ImageView? = null
        init {
            nombre = vista.findViewById(R.id.tvNombre)
            imagen = vista.findViewById(R.id.imageView)
        }
    }

    init {
        this.frutas = items
    }


}