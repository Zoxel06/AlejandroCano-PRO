import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.random.Random

fun main() {

    val out = OutputStreamWriter(System.out)
    val input = BufferedReader(InputStreamReader(System.`in`))

    val modalidad = input.readLine()

    if (modalidad == "1") {
        val opciones = arrayOf("piedra", "papel", "tijera")

        val respuestaHijo = opciones.random()

        val respuestaPadre = input.readLine().lowercase()

        var resultado = ""

        if (respuestaPadre == respuestaHijo) {
            resultado = "Empate"
        } else if (respuestaPadre == "piedra" && respuestaHijo == "tijera") {
            resultado = "Has ganado"
        } else if (respuestaPadre == "papel" && respuestaHijo == "piedra") {
            resultado = "Has ganado"
        } else if (respuestaPadre == "tijera" && respuestaHijo == "papel") {
            resultado = "Has ganado"
        } else {
            resultado = "Has perdido"
        }

        out.write("El hijo ha sacado $respuestaHijo y tu has sacado $respuestaPadre --> $resultado\n")
        out.flush()

    } else if (modalidad == "2") {
        val random = (1..5).random()

        val eleccionPadre = input.readLine().toInt()

        var resultado = ""

        if (eleccionPadre == random) {
            resultado = "Has ganado, lo has adivinado"
        } else {
            resultado = "Has perdido, el numero secreto era $random"
        }

        out.write(resultado)
        out.flush()
    }


}