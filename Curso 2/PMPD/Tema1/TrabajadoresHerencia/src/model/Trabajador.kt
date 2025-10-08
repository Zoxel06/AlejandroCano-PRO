package model

abstract class Trabajador(
    nombre: String,
    apellido: String,
    dni: String,
    var salario: Double,
    telefono: Int? = null,
    correo: String? = null,
    var seguro: Boolean? = false // el boolean ya es false por defecto asi que no hay que igualarlo a false
) : Persona(nombre, apellido, dni, telefono, correo) {

    // telefono, correo, SEGURO -> Optativo

    /*
    constructor(nombre: String,apellido: String,dni: String,salario: Int,seguro: Boolean) : this (nombre,apellido,dni,salario) {
        this.seguro = seguro
        this.telefono = telefono
        this.correo = correo
    }
     */

    // nombre, apellido, dni, SALARIO -> Obligatorios
    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Salario: $salario")
        println("Seguro: $seguro")
    }

      abstract fun calcularSalarioNeto() : Double;

}