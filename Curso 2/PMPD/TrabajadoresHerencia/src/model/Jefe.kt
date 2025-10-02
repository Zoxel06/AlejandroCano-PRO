package model

class Jefe(
    nombre: String,
    apellido: String,
    dni: String,
    salario: Double,
    var nResponsabilidad: Int
) : Trabajador(nombre, apellido, dni, salario) {

    override fun calcularSalarioNeto(): Double {
        val salarioNeto: Double = 0.0

        if (nResponsabilidad >= 3) {
            salario += (salario * 0.03)
            println("Te hemos subido el salario un 3%")
        } else {
            salario -= (salario * 0.03)
            println("Te hemos bajado el salario un 3%")
        }

        return salarioNeto
    }

    fun disminuirResponsabilidad() {
        if (nResponsabilidad > 0) {
            nResponsabilidad--
            println("Se ha reducido 1 de tu nivel responsabilidad")
        }
    }

    fun aumentarResponsabilidad() {
        if (nResponsabilidad < 5) {
            nResponsabilidad++
            println("Se ha aumentado 1 de tu nivel responsabilidad")
        }
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("nResponsabilidad: $nResponsabilidad")
    }

}