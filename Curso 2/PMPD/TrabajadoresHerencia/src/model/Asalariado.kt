package model

class Asalariado(
    nombre: String,
    apellido: String,
    dni: String,
    salario: Double,
    var nPagas: Int,
    var irpf: Double
) : Trabajador(nombre, apellido, dni, salario){

    override fun calcularSalarioNeto(): Double {
        // salario total - lo que te quitan de IRPF
        var salarioNeto: Double = salario -(salario*irpf)

        return salarioNeto
    }

    // un asalariado puede pedir un aumento de sueldo
        // se genera un aleatorio entre 1-10
        // en caso de generar un numero <5 NO se le sube el salario
        // en caso de generar un numero >=5 SI se le sube un 10% el salario

    fun pedirAumento(){
        val random : Int = (1..10).random()

        if (random < 5) {
            println("Lo siento, no te subimos el sueldo")
        } else {
            var salarioAntiguo: Double = salario
            salario += (salario*0.10) // salario *= 1.1 es lo mismo
            println("Te subimos el sueldo un de $salarioAntiguo a $salario")
        }
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("nPagas: $nPagas")
        println("IRPF: $irpf")
    }

}