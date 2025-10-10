package carrera

import carrera.Liebre
import carrera.Tortuga

fun main() {

    var tortuga = Tortuga()
    var liebre = Liebre()

    println("La carrera ha empezado")

    tortuga.start()
    liebre.start()

    Thread.sleep(5000)
    liebre.interrupt()

    tortuga.join()  // El join hace que acabe su proceso para que lo que escriba despues se imprima u ocurra despues, y no antes
    liebre.join()

    println("La carrera ha acabado")

}