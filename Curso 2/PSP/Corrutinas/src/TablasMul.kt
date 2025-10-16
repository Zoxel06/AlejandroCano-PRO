import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        val jobs = mutableListOf<Job>()
        for (tabla in 1..10) {
            val job = launch {
                println("Tabla del $tabla")
                for (i in 1..10)
                    println("$tabla x $i = " + tabla * i)
            }
            jobs.add(job)
        }
    }

}