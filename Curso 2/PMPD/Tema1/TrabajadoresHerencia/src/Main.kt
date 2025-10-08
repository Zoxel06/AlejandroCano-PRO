import model.Asalariado
import model.Autonomo
import model.Directivo
import model.Jefe
import model.Persona
import model.Sindicato
import model.Trabajador

fun main() {

    var asalariado: Asalariado = Asalariado("Asalariado","1","1234A",1000.0,5,21.0)
    var autonomo: Autonomo = Autonomo("Autonomo","1","1234A",2000.0,true,3)
    var jefe: Jefe = Jefe("Jefe","1","1234A",5000.0,1)
    var directivo: Directivo = Directivo("Directivo","1","1234A",123123,"directivo@gmail.com",54)

    val trabajadores = arrayListOf<Persona>(asalariado,autonomo,jefe,directivo)

    // calcular beneficios

    // Para poder bajar sueldos, un jefe solo bajara los suelos
        // a los trabajadores que no son jefes
        // la cantidad de salario que puede bajar es del 10%
    // En caso de ser un directivo, le podré bajar el sueldo a todos los trabajadores incluido a los jefes:
        // un 20% a los Asalariados/Autonomos
        // un 10% a los jefes

    trabajadores.forEach {
        it.mostrarDatos()
        if (it is Trabajador){
            println("Calculando salario del trabajador: ${it.calcularSalarioNeto()}")
        }
    }

    trabajadores.forEach {
        if (it is Sindicato){
            it.calcularBeneficios()
        }
    }

    trabajadores.forEach {
        if (it is Sindicato){
            it.bajarSueldos(trabajadores.filter { it !is Directivo } as ArrayList<Trabajador>)
        }
    }

}