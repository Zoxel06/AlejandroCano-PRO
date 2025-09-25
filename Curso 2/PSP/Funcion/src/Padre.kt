import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val proceso = ProcessBuilder("C:\\Users\\Usuario DAM2\\.jdks\\openjdk-24.0.2+12-54\\bin\\java.exe\" \"-javaagent:C:\\Users\\Usuario DAM2\\AppData\\Local\\Programs\\IntelliJ IDEA Community Edition 2025.2.1\\lib\\idea_rt.jar=59852\" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath \"C:\\Users\\Usuario DAM2\\Documents\\GitHub\\AlejandroCano\\AlejandroCano-PRO\\Curso 2\\PSP\\Funcion\\out\\production\\Funcion;C:\\Users\\Usuario DAM2\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\2.2.0\\kotlin-stdlib-2.2.0.jar;C:\\Users\\Usuario DAM2\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar\" HijoKt")
    val procesoEjecutado = proceso.start()

    val output = OutputStreamWriter(procesoEjecutado.outputStream)
    output.write("Hola hijo\n")
    output.flush()

    val input = BufferedReader(InputStreamReader(procesoEjecutado.inputStream)).readLine()

    println(input)
}