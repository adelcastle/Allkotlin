package com.example.myapplication.Clases


class Ciudad( name:String,weather:ArrayList<Weather>,main:Main) {

    var name:String = name
    var weather:ArrayList<Weather> = weather
    var main:Main = main

init{
    this.name=name
    this.weather=weather
    this.main=main
}



}

class Weather(description:String,icon:String){

    var description:String = description
    var icon:String = icon
    init{
        this.description=description
        this.icon=icon
    }

}
class Main(temp:Float,humidity:Float){
    var temp:Float = temp
    var humidity:Float = humidity
    init{
        this.temp=temp
        this.humidity=humidity
    }
}