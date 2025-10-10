package descargas
import java.util.concurrent.TimeUnit
import kotlin.time.measureTime

// La ejecución sin hilos es más LENTA

fun main() {
    val tiempo = measureTime {
        for (i in 1..3){
            descargar()
        }
    }

    println(tiempo)
}


fun descargar(){

    println("Comienzo a descargar")

    TimeUnit.SECONDS.sleep(2)

    println("Ya lo he descargado")
}