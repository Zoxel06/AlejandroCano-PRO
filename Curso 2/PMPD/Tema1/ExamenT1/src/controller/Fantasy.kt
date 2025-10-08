package controller

import model.Jugador
import model.Participante

class Fantasy {

    var plantilla: ArrayList<Jugador>? = null
    var participantes: ArrayList<Participante> = arrayListOf<Participante>()
    var jugadores: ArrayList<Jugador> = arrayListOf<Jugador>()

    fun generarJugadores() {
        jugadores.add(Jugador(1, "Marc-André ter Stegen", "Portero", 3000000,10))
        jugadores.add(Jugador(2, "Ronald Araújo", "Defensa", 4000000,0))
        jugadores.add(Jugador(3, "Eric García", "Defensa", 1000000,3))
        jugadores.add(Jugador(4, "Pedri", "Mediocentro", 5000000,23))
        jugadores.add(Jugador(5, "Robert Lewandowski", "Delantero", 8000000,15))
        jugadores.add(Jugador(6, "Courtois", "Portero", 3000000,1))
        jugadores.add(Jugador(7, "David Alaba", "Defensa", 4000000,5))
        jugadores.add(Jugador(8, "Jesús Vallejo", "Defensa", 500000,10))
        jugadores.add(Jugador(9, "Luka Modric", "Mediocentro", 5000000,5))
        jugadores.add(Jugador(10, "Karim Benzema", "Delantero", 8000000,10))
        jugadores.add(Jugador(11, "Ledesma", "Portero", 500000,6))
        jugadores.add(Jugador(12, "Juan Cala", "Defensa", 300000,3))
        jugadores.add(Jugador(13, "Zaldua", "Defensa", 400000,6))
        jugadores.add(Jugador(14, "Alez Fernández", "Mediocentro", 700000,9))
        jugadores.add(Jugador(15, "Choco Lozano", "Delantero", 800000,4))
        jugadores.add(Jugador(16, "Rajković", "Portero", 300000,3))
        jugadores.add(Jugador(17, "Raíllo", "Defensa", 200000,6))
        jugadores.add(Jugador(18, "Maffeo", "Defensa", 300000,0))
        jugadores.add(Jugador(19, "Ruiz de Galarreta", "Mediocentro", 400000,7))
        jugadores.add(Jugador(20, "Remiro", "Portero", 1000000,4))
        jugadores.add(Jugador(21, "Elustondo", "Defensa", 900000,3))
        jugadores.add(Jugador(22, "Zubeldia", "Defensa", 800000,5))
        jugadores.add(Jugador(23, "Zubimendi", "Mediocentro", 1000000,6))
        jugadores.add(Jugador(24, "Take Kubo", "Delantero", 800000,7))
    }

    fun agregarParticipante(participante: Participante) {
        participantes.add(participante)
        println("Participante creado correctamente")
    }

    fun listarJugadores3M() {
        jugadores.forEach {
            if (it.valor > 3000000) {
                println()
                println("Id = ${it.id}")
                println("Nombre = ${it.nombre}")
                println("Posicion = ${it.posicion}")
                println("Valor = ${it.valor}")
                println()
            }
        }
    }

    fun mostrarGanador() {
        var ganador: Participante = Participante(100,"Nadie", puntos = 0, presupuesto = 0)
        participantes.forEach {
            if (it.puntos > ganador.puntos){
                ganador = it
            }
        }

        println("El ganador es el participante ${ganador.id} ${ganador.nombre} con ${ganador.puntos} puntos")
    }

}