package model

class Autonomo(
    nombre: String,
    apellido: String,
    dni: String,
    salario: Double,
    seguro: Boolean,
    var cuotasSS: Int,
) : Trabajador(nombre, apellido, dni, salario, seguro = seguro) {

    override fun calcularSalarioNeto(): Double {
        val salarioNeto: Double = salario - (cuotasSS * 12)
        return salarioNeto
    }

    fun pedirDescuento() {
        if (seguro == true){
            cuotasSS  *= 0.5.toInt()
            println("Te hemos descontado las cuotas a la mitad")
        } else {
            println("No tienes seguro por lo que no te hemos condedido ningun descuento")
        }
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("CuotasSS: $cuotasSS")
    }

}