package model

class Jefe(
    nombre: String,
    apellido: String,
    dni: String,
    salario: Double,
    var nResponsabilidad: Int
) : Trabajador(nombre, apellido, dni, salario),
    Sindicato {

    override fun calcularSalarioNeto(): Double {

        if (nResponsabilidad >= 3) {
            salario += (salario * 0.03)
            println("Te hemos subido el salario un 3%")
        } else {
            salario -= (salario * 0.03)
            println("Te hemos bajado el salario un 3%")
        }

        return salario
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

    override fun bajarSueldos(lista: ArrayList<Trabajador>): Boolean {
        println("Procedes a bajar los sueldos, eres jefe y puedes hacerlo")
        lista.forEach {
            if (it !is Jefe){
                it.salario *= 0.90
            }
        }
        return true
    }

    override fun calcularBeneficios(): Double {
        println("Como jefe, vas a calcular el beneficio de la empresa")
        return 0.0
    }

}