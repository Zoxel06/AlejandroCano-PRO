package carrera

class Tortuga : Thread() {

    override fun run() {
        println("Tortuga empieza")
        for (i in 1..10){
            println("Tortuga corriendo")
            sleep(2000)
        }
        println("Tortuga acaba")
    }


}