package model

open class Arma(
    var id: String,
    var nombre: String,
    var nivelPotencia: Int,
    var nivelDanio: Int
) {

    open fun mostrarDatos(){
        println("id = ${id}")
        println("nombre = ${nombre}")
        println("nivelPotencia = ${nivelPotencia}")
        println("nivelDanio = ${nivelDanio}")
    }

}