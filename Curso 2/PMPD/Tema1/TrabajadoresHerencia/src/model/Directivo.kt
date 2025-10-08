package model

class Directivo(
    nombre: String,
    apellido: String,
    dni: String,
    telefono: Int,
    correo: String,
    var nAcciones: Int
) : Persona(nombre, apellido, dni, telefono, correo),
    Sindicato {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("nAcciones = ${nAcciones}")
    }

    fun venderAcciones(acciones: Int) {
        if (acciones > nAcciones) {
            println("No puedes vender tantas acciones")
        } else {
            nAcciones -= acciones
            println("Numero de acciones actualizadas")
        }
    }

    override fun bajarSueldos(lista: ArrayList<Trabajador>): Boolean {

        lista.forEach {
            if (it is Jefe) {
                it.salario *= 0.90 // lo mismo que poner -> (it.salario - it.salario * 0.10)
            } else if (it is Asalariado) {
                it.salario *= 0.80
            } else if (it is Autonomo) {
                it.salario *= 0.80
            }
        }

        return true
    }

    override fun calcularBeneficios(): Double {
        println("Como directivo, vas a calcular el beneficio de la empresa")
        return 0.0
    }

}