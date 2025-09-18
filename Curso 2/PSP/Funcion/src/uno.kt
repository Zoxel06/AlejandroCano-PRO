import java.io.BufferedReader
import java.io.InputStreamReader


fun main(args: Array<String>) {

ejercicio3()


}


fun ejercicio1() {
    val nombre = "arp -a"
    // val proceso = ProcessBuilder("cmd.exe","/c","arp -a")
    val proceso = ProcessBuilder("notepad")
    // proceso.redirectOutput(ProcessBuilder.Redirect.INHERIT)
    // proceso.redirectError(ProcessBuilder.Redirect.INHERIT)
    // proceso.start()
    val procesoTemporal = proceso.start()
    //print(procesoTemporal.inputStream.bufferedReader().readText())
    print(procesoTemporal.isAlive)
    procesoTemporal.waitFor()
}

fun ejercicio2() {
    val nombre = "arp -a"
    val proceso = ProcessBuilder("cmd.exe", "/c", "arp -a")
    val procesoTemporal = proceso.start()
    val lector = BufferedReader(InputStreamReader(procesoTemporal.inputStream))
    var linea: String?
    while (lector.readLine().also { linea = it } != null) {
        println(linea)
    }
}

fun ejercicio3(){
    // ESCRIBIR EL PRINT DE OTRA CLASE LLAMANDOLA DESDE ESTA

    var linea = BufferedReader(InputStreamReader(ProcessBuilder("C:\\Users\\Usuario DAM2\\.jdks\\openjdk-24.0.2+12-54\\bin\\java.exe\" \"-javaagent:C:\\Users\\Usuario DAM2\\AppData\\Local\\Programs\\IntelliJ IDEA Community Edition 2025.2.1\\lib\\idea_rt.jar=57920\" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath \"C:\\Users\\Usuario DAM2\\Documents\\GitHub\\AlejandroCano\\AlejandroCano-PRO\\Curso 2\\PSP\\Funcion\\out\\production\\Funcion;C:\\Users\\Usuario DAM2\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\2.2.0\\kotlin-stdlib-2.2.0.jar;C:\\Users\\Usuario DAM2\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar\" LlamarKt").start().inputStream)).readLine()
    println(linea.uppercase())

}