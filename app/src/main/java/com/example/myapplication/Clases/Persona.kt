package com.example.myapplication.Clases

class Personas(personas:ArrayList<Persona>){
    var personas = ArrayList<Persona>()

    init {
        this.personas = personas
    }
}

class Persona (nombre:String,pais:String,estado:String,experiencia:Int) {

    var nombre: String = ""
    var pais: String = ""
    var estado: String = ""
    var experiencia: Int = 0

    init {
        this.nombre = nombre
        this.pais = pais
        this.estado = estado
        this.experiencia = experiencia


    }
}