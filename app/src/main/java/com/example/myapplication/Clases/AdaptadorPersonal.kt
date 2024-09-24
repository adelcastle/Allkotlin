package com.example.myapplication.Clases

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RemoteViews.RemoteCollectionItems
import android.widget.TextView
import com.example.myapplication.Fruta
import com.example.myapplication.R

class AdaptadorPersonal(var contexto: Context, items:ArrayList<Fruta>): BaseAdapter() {

    var items:ArrayList<Fruta>? = null
    init {
        this.items = items
    }

    override fun getCount(): Int {
        return items?.count()!!

    }

    override fun getItem(p0: Int): Any {
            return items?.get(p0)!!
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var holder: ViewHolder? = null
        var vista: View? = p1
        if(vista == null){
            vista = View.inflate(contexto, R.layout.templetecelda, null)
            holder = ViewHolder(vista)
            vista.tag = holder
        }else{
            holder=vista.tag as? ViewHolder
        }
        val item = items?.get(p0) as? Fruta
        holder?.nombre?.text = item?.nombre
        holder?.imagen?.setImageResource(item?.imagen!!)
        return vista!!
    }

    private class ViewHolder(vista:View){
        var nombre: TextView? = null
        var imagen: ImageView? = null

        init {
            nombre = vista.findViewById(R.id.tvnombre)
            imagen = vista.findViewById(R.id.imagen)
        }


    }

}