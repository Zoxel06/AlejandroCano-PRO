package model

import controller.Fantasy

class Participante (
    var id: Int,
    var nombre: String,
    var plantilla: ArrayList<Jugador> ?= null,
    var puntos: Int,
    var presupuesto: Int
){

    fun ficharJugador(fantasy: Fantasy, id: Int){
        fantasy.jugadores.forEach {
            if (it.id == id && presupuesto > it.valor){
                plantilla?.add(it)
                presupuesto -= it.valor
                puntos += it.puntos
                println("Jugador fichado con exito")
            }
        }
    }

}