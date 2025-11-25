import java.awt.MouseInfo
import java.net.InetAddress
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.awt.Robot

fun main() {
    val puerto = 6000 // Puerto del servidor

    try {
        val inetAddress: InetAddress = InetAddress.getByName("192.168.2.115")
        val servidor = ServerSocket(puerto, 6000, inetAddress)
        println("Escuchando en ${servidor.localPort}")

        val robot = Robot()

        while (true) {
            val cliente = servidor.accept()
            val inputStream = cliente.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val mensajeRecibido = bufferedReader.readLine()
            println("Mensaje recibido del cliente ${cliente.inetAddress.hostAddress}:${cliente.port}: $mensajeRecibido")

            interpretarDireccion(mensajeRecibido, robot)

            bufferedReader.close()
            cliente.close()
        }

        // servidor.close() // No alcanzable con el ciclo while(true)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun interpretarDireccion(direccion: String?, robot: Robot) {
    direccion?.let {
        val currentPosition = MouseInfo.getPointerInfo().location
        val x = currentPosition.x
        val y = currentPosition.y

        println(it)

        when (it) {
            "izquierda" -> {
                robot.mouseMove(x - 10, y) // Mueve el cursor hacia la izquierda
            }
            "derecha" -> {
                robot.mouseMove(x + 10, y) // Mueve el cursor hacia la derecha
            }
            "arriba" -> {
                robot.mouseMove(x, y - 10) // Mueve el cursor hacia arriba
            }
            "abajo" -> {
                robot.mouseMove(x, y + 10) // Mueve el cursor hacia abajo
            }
            else -> {
            }
        }
    }
}