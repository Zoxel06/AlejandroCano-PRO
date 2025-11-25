// Archivo: RemoteMouseServer.kt
import java.awt.MouseInfo
import java.awt.Robot
import java.awt.event.InputEvent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.ServerSocket

fun main() {
    val puerto = 6000
    val password = "1234" // Contraseña simple para seguridad

    try {
        val inetAddress: InetAddress = InetAddress.getByName("0.0.0.0") // Escucha en todas las interfaces
        val servidor = ServerSocket(puerto, 50, inetAddress)
        println("Servidor escuchando en ${servidor.localPort}")

        val robot = Robot()

        while (true) {
            val cliente = servidor.accept()
            val inputStream = cliente.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val mensajeRecibido = bufferedReader.readLine()
            println("Mensaje recibido: $mensajeRecibido")

            // Verificar contraseña simple
            if (mensajeRecibido.startsWith(password)) {
                val comando = mensajeRecibido.removePrefix(password)
                interpretarComando(comando, robot)
            } else {
                println("Contraseña incorrecta, ignorando comando")
            }

            bufferedReader.close()
            cliente.close()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun interpretarComando(comando: String, robot: Robot) {
    when {
        comando == "click" -> {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
        }
        comando.startsWith("move") -> {
            // Formato: move,dx,dy
            val partes = comando.split(",")
            if (partes.size == 3) {
                val dx = partes[1].toIntOrNull() ?: 0
                val dy = partes[2].toIntOrNull() ?: 0
                moverMouse(dx, dy, robot)
            }
        }
        comando.startsWith("scroll") -> {
            // Formato: scroll,dy
            val partes = comando.split(",")
            if (partes.size == 2) {
                val dy = partes[1].toIntOrNull() ?: 0
                robot.mouseWheel(dy)
            }
        }
    }
}

fun moverMouse(dx: Int, dy: Int, robot: Robot) {
    val pos = MouseInfo.getPointerInfo().location
    val x = pos.x + dx
    val y = pos.y + dy
    robot.mouseMove(x, y)
}
