import controller.Fantasy
import model.Administrador
import model.Jugador
import model.Participante

fun main() {

    var fantasy: Fantasy = Fantasy()

    fantasy.generarJugadores()

    var participante1: Participante = Participante(1,"Participante1", puntos = 0, presupuesto = 10000000)
    var participante2: Participante = Participante(2,"Participante2",puntos = 0, presupuesto = 10000000)
    var participante3: Participante = Participante(3,"Participante3",puntos = 0, presupuesto = 10000000)
    var participante4: Participante = Participante(4,"Participante4",puntos = 0, presupuesto = 10000000)

    fantasy.agregarParticipante(participante1)
    fantasy.agregarParticipante(participante2)
    fantasy.agregarParticipante(participante3)
    fantasy.agregarParticipante(participante4)

    participante1.ficharJugador(fantasy,1)
    participante1.ficharJugador(fantasy,2)
    participante1.ficharJugador(fantasy,3)
    participante1.ficharJugador(fantasy,4)
    participante1.ficharJugador(fantasy,5)
    participante1.ficharJugador(fantasy,6)

    participante2.ficharJugador(fantasy,7)
    participante2.ficharJugador(fantasy,8)
    participante2.ficharJugador(fantasy,9)
    participante2.ficharJugador(fantasy,10)
    participante2.ficharJugador(fantasy,11)
    participante2.ficharJugador(fantasy,12)

    participante3.ficharJugador(fantasy,13)
    participante3.ficharJugador(fantasy,14)
    participante3.ficharJugador(fantasy,15)
    participante3.ficharJugador(fantasy,16)
    participante3.ficharJugador(fantasy,17)
    participante3.ficharJugador(fantasy,18)

    participante4.ficharJugador(fantasy,19)
    participante4.ficharJugador(fantasy,20)
    participante4.ficharJugador(fantasy,21)
    participante4.ficharJugador(fantasy,22)
    participante4.ficharJugador(fantasy,23)

    var administrador: Administrador = Administrador(1,"Administrador",1234)

    fantasy.listarJugadores3M()


    if (administrador.iniciarJuego(1234)){
        fantasy.mostrarGanador()
    }

}