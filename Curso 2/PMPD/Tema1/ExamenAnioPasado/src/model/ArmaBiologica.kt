package model

class ArmaBiologica(
    id: String,
    nombre: String,
    nivelPotencia: Int,
    nivelDanio: Int,
    var descripcion: String
): Arma(
    id, nombre, nivelPotencia, nivelDanio
){

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("descripcion = ${descripcion}")
    }

}