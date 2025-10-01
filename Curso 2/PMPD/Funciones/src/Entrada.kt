fun main() {

    // comentario de linea
    /*
       comentario multiple
     */

    println("Este es mi primer programa Kotlin")

    var nombre = "Alejandro" // String
    var edad = 19 // Int
    var correo: String? = null
    lateinit var direccion: String
    val DNI = "123123A"

    /*
    println("Por favor introduce tu nombre")
    nombre = readln()
    println("Por favor introduce tu edad")
    edad = readln().toInt()


     */

    /*
    // Hola soy Alejandro, tengo 19 años y mi DNI es 123123A

    println("Hola soy $nombre, tengo $edad años y mi DNI es $DNI")
    println("Mi nombre tiene ${nombre.length} letras")
    println("Mi correo es ${correo?.length?:"sin asignar"}")
    direccion = "Mostoles"
    println("Mi direccion es $direccion")

     */

    //saludar(vecesParam = 2)

    // println("El resultado de la operacion es ${sumar(op2 = 7)}")

    // sumaFlecha = null
    // sumaFlecha?.invoke(7,33) ?: "asfasfdasd"

    //decidirWhen(1)

    repetirFor()

}

fun saludar(nombreParam: String = "Sin nombre", vecesParam: Int) {
    println("Hola $nombreParam, acabas de realizar una funcion")
}

fun sumar(op1: Int = 0, op2: Int = 0): Int {
    return op1.and(op2)
}

var sumaFlecha: ((Int, Int) -> Unit)? = { p1: Int, p2: Int ->
    println("El resultado de la suma es ${p1 + p2}")
}

fun decidirWhen(valor: Int) {
    println("El valor analizado es: ")
    when (valor) {
        1 -> {
            println("El caso tiene como valor 1")
        }

        2 -> {
            println("El caso tiene como valor 2")
        }

        3 -> {
            println("El caso tiene como valor 3")
        }

        4 -> {
            println("El caso tiene como valor 4")
        }

        else -> {
            println("El valor no está dentro de la lista de opciones")
        }
    }
}

fun repetirFor() {
    /*for (i in 0..9){
        println(i)
    }*/

    //(20..30).forEach { p -> println(p) }
    (20..30).forEachIndexed { index, item -> println("Elemento en posicion $index") }

}