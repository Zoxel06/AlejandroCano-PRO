import java.net.InetAddress
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val puerto = 6000 // Puerto del servidor

    try {
        val inetAddress: InetAddress = InetAddress.getByName("192.168.2.115")
        val servidor = ServerSocket(puerto, 6000, inetAddress)
        println("Escuchando en ${servidor.localPort}")

        while (true) {
            val cliente = servidor.accept()
            val inputStream = cliente.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val mensajeRecibido = bufferedReader.readLine()
            println("Mensaje recibido del cliente ${cliente.inetAddress.hostAddress}:${cliente.port}: $mensajeRecibido")

            bufferedReader.close()
            cliente.close()
        }

        // servidor.close() // No alcanzable con el ciclo while(true)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}