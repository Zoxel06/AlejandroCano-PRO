import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val input = BufferedReader(InputStreamReader(System.`in`))
    val output = OutputStreamWriter(System.out)

    output.write(input.readLine().uppercase())
    output.flush() // SIRVE PARA VACIAR EL BUFFEREDREADER ANTES DE ENVIAR EL MENSAJE, SI NO SE VACIA ANTES NO LO PUEDE LEER

    // El output tiene la misma funcion que esto:
    // println(input.readLine().uppercase())  --> El println ya hace el flush (limpiado) automaticamente

}