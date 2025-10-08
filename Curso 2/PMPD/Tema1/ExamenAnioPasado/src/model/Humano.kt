package model

class Humano(
    id: String,
    nombre: String,
    nivel: Int,
    var resistencia: Int,
    var armaTradicional: ArmaTradicional
): Superheroe(
    id, nombre, nivel
) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("resistencia = ${resistencia}")
        println("armaTradicional = ${armaTradicional}")
    }

}