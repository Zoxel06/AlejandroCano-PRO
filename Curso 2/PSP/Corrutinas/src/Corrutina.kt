import kotlinx.coroutines.*

fun main() {

    runBlocking {
        val jobs = launch {
            println("Inicio de corrutina")
            for (i in 1..10){
                println("Estoy ejecutando la corrutina")
                delay(1000)
            }
        }
        jobs.join()

        for (i in 1..10){
            println("Estoy ejecutando el principal")
            Thread.sleep(1000)
        }
    }

}