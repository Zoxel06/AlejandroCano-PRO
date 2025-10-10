package descargas
import java.util.concurrent.TimeUnit
import kotlin.time.measureTime

// La ejecución con hilos es más RÁPIDA

fun main() {
    val tiempo = measureTime {
       val hilos = listOf(
           Thread {descargar2()}, Thread {descargar2()}, Thread {descargar2()}, Thread {descargar2()},
           Thread {descargar2()}, Thread {descargar2()}, Thread {descargar2()}, Thread {descargar2()},
           Thread {descargar2()}, Thread {descargar2()}, Thread {descargar2()}, Thread {descargar2()}
       )
        hilos.forEach {
            it.start()
        }
        hilos.forEach {
            it.join()
        }
    }

    println(tiempo)
}

fun descargar2(){

    TimeUnit.SECONDS.sleep(2)

}
