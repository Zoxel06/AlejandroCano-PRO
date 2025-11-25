import java.net.Socket
import java.io.OutputStreamWriter
import java.io.BufferedWriter

fun main() {
    val host = "192.168.137.56" // Cambia por la IP local de tu móvil
    val puerto = 6000
    val mensaje = "Hola desde el PC"

    try {
        Socket(host, puerto).use { socket ->
            BufferedWriter(OutputStreamWriter(socket.getOutputStream())).use { writer ->
                writer.write(mensaje)
                writer.newLine() // Muy importante para readLine()
                writer.flush()
            }
        }
        println("Mensaje enviado correctamente a $host:$puerto")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
