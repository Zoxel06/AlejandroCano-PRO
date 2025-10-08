import model.Superheroe

fun main() {

    do {
        println("1. Crear Superheroe")
        println("2. Listar Superheroes")
        println("3. Listar armas")
        println("4. Enfrentar superheroes")
        println("5. Salir")
        println("Elije una opcion")

        val opcion: Int = readln().toInt()

        when (opcion) {
            1 -> {
                println("Que tipo de superheroe quieres crear (1-Humano/2-Mutante)")
                var respuesta: Int = readln().toInt()
                if (respuesta == 1) {

                } else if (respuesta == 2) {

                } else {
                    println("Opcion no contemplada")
                }
            }

            2 -> {

            }

            3 -> {

            }

            4 -> {

            }

            5 -> println("Saliendo...")

            else -> println("Opcion no contemplada")

        }

    } while (opcion != 5)


}