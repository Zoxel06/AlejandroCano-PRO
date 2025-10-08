package model

open class Superheroe(
    var id: String,
    var nombre: String,
    var nivel: Int,
    var listaSuperheroe: ArrayList<Superheroe> = arrayListOf<Superheroe>()
) {

    open fun mostrarDatos(){
        println("id = ${id}")
        println("nombre = ${nombre}")
        println("nivel = ${nivel}")
    }

}