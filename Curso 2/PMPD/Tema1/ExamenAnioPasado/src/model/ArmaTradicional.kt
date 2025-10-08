package model

class ArmaTradicional(
    id: String,
    nombre: String,
    nivelPotencia: Int,
    nivelDanio: Int,
    var peso: Float
): Arma (
    id, nombre, nivelPotencia, nivelDanio
) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("peso = ${peso}")
    }
}