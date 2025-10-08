package model

abstract class Persona(
    var nombre: String,
    var apellido: String,
    var dni: String,
    var telefono: Int? = null,
    var correo: String? = null
) {

    open fun mostrarDatos() {
        println("Nombre: $nombre")
        println("Apellido: $apellido")
        println("DNI: $dni")
        println("Telefono: ${telefono?: "No especificado"}")
        println("Correo: ${correo?: "No especificado"}")
    }

}