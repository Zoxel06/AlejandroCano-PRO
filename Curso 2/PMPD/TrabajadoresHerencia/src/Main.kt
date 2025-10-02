import model.Asalariado
import model.Trabajador

fun main() {

    var asalariado: Asalariado = Asalariado("Asalariado","1","1234A",100.0,5,21.0)

    asalariado.pedirAumento()
    asalariado.pedirAumento()
    asalariado.pedirAumento()
    asalariado.pedirAumento()
    asalariado.mostrarDatos()

}