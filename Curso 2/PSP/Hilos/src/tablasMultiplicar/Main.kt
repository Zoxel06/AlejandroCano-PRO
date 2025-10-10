package tablasMultiplicar

import kotlin.system.measureTimeMillis

fun main() {

    var tablas = Tablas()

    val tiempo = measureTimeMillis {
        val hilos = listOf(Thread {tablas.tabla1()},Thread {tablas.tabla2()},Thread {tablas.tabla3()},Thread {tablas.tabla4()},Thread {tablas.tabla5()},
            Thread {tablas.tabla6()}, Thread {tablas.tabla7()},Thread {tablas.tabla8()},Thread {tablas.tabla9()},Thread {tablas.tabla10()})

        hilos.forEach {
            it.start()
            it.join()
        }

    }

    println("Tarda: $tiempo")

    }