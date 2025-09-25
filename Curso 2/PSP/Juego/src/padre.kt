import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val proceso =
        ProcessBuilder("C:\\Users\\Usuario DAM2\\.jdks\\openjdk-24.0.2+12-54\\bin\\java.exe\" \"-javaagent:C:\\Users\\Usuario DAM2\\AppData\\Local\\Programs\\IntelliJ IDEA Community Edition 2025.2.1\\lib\\idea_rt.jar=51917\" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath \"C:\\Users\\Usuario DAM2\\Documents\\GitHub\\AlejandroCano\\AlejandroCano-PRO\\Curso 2\\PSP\\Juego\\out\\production\\Juego;C:\\Users\\Usuario DAM2\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\2.2.0\\kotlin-stdlib-2.2.0.jar;C:\\Users\\Usuario DAM2\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar\" HijoKt")
    val procesoArrancado = proceso.start()

    val input = BufferedReader(InputStreamReader(procesoArrancado.inputStream))
    val out = OutputStreamWriter(procesoArrancado.outputStream)

    print("Elije un modo de juego (1 o 2): ")
    val modalidad = readln()
    out.write(modalidad + "\n")
    out.flush()


    if (modalidad == "1") {
        print("Elige una opcion (piedra, papel o tijera): \n")
        val respuestaPadre = readln()

        out.write(respuestaPadre + "\n")
        out.flush()

        val resultado = input.readLine()
        print(resultado)

    } else if (modalidad == "2") {
        print("Adivina que numero estoy pensando del 1 al 5: \n")
        val eleccionPadre = readln().toInt()

        out.write("$eleccionPadre\n")
        out.flush()

        val resultado = input.readLine()
        print(resultado)

    } else {
        print("Modalidad no existente")
    }


}