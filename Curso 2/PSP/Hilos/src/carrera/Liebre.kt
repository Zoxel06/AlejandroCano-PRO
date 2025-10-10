package carrera

class Liebre : Thread() {

    override fun run() {

        println("Liebre empieza")
        try {
            while (!currentThread().isInterrupted) {
                for (i in 1..10) {
                    println("Liebre corriendo")
                    sleep(1000)
                }
                println("Liebre acaba")
            }
        } catch (e: InterruptedException) {
            println("Liebre muerta")
        }


    }


}