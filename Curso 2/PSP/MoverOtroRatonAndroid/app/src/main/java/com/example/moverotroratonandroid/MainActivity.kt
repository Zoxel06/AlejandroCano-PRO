package com.example.moverotroratonandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.example.moverotroratonandroid.ui.theme.MoverOtroRatonAndroidTheme
import java.net.Socket

class MainActivity : ComponentActivity() {

    private val host = "192.168.2.135" // Cambiar por la IP pública o VPN
    private val port = 6000
    private val password = "1234" // Debe coincidir con la del servidor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoverOtroRatonAndroidTheme {
                Touchpad(host, port, password)
            }
        }
    }
}

@Composable
fun Touchpad(host: String, port: Int, password: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                val (dx, dy) = dragAmount
                sendCommand(host, port, "$password" + ",move,${dx.toInt()},${dy.toInt()}")
            }
        }
        .clickable {
            sendCommand(host, port, "$password" + "click")
        }
    )
}

fun sendCommand(host: String, port: Int, command: String) {
    Thread {
        try {
            val socket = Socket(host, port)
            val outputStream = socket.getOutputStream()
            outputStream.write(command.toByteArray())
            outputStream.flush()
            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.start()
}
