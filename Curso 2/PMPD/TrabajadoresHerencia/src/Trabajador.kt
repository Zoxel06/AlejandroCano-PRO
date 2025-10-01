import model.Persona

class Trabajador(
    nombre: String,
    apellido: String,
    dni: String,
    var salario: Int,
    var nPagas: Int,
    telefono: Int? = null,
    correo: String? = null,
    var seguro: Boolean? = false // el boolean ya es false por defecto asi que no hay que igualarlo a false
) : Persona(nombre,apellido,dni,telefono,correo){

    // nombre, apellido, dni, SALARIO, NPAGAS -> Obligatorios
    // telefono, correo, SEGURO -> Optativo

    /*
    constructor(nombre: String,apellido: String,dni: String,salario: Int,nPagas: Int,seguro: Boolean) : this (nombre,apellido,dni,salario,nPagas) {
        this.seguro = seguro
        this.telefono = telefono
        this.correo = correo
    }
     */

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Salario: $salario")
        println("Pagas: $nPagas")
        println("Seguro: $seguro")
    }


}