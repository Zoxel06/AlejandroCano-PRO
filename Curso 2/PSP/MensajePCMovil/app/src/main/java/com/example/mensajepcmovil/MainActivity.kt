package com.example.mensajepcmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

class MainActivity : ComponentActivity() {

    private var serverThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var mensajeRecibido by remember { mutableStateOf("Esperando mensaje...") }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Servidor TCP",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = mensajeRecibido,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(onClick = {
                        if (serverThread == null || !serverThread!!.isAlive) {
                            serverThread = Thread {
                                try {
                                    val serverSocket = ServerSocket(6000)
                                    runOnUiThread {
                                        mensajeRecibido = "Esperando conexión en puerto 6000..."
                                    }

                                    while (true) {
                                        val client = serverSocket.accept()
                                        val input = BufferedReader(InputStreamReader(client.getInputStream()))
                                        val mensaje = input.readLine()
                                        runOnUiThread {
                                            mensajeRecibido = "Mensaje recibido: $mensaje"
                                        }
                                        input.close()
                                        client.close()
                                    }

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    runOnUiThread {
                                        mensajeRecibido = "Error: ${e.message}"
                                    }
                                }
                            }
                            serverThread!!.start()
                        } else {
                            mensajeRecibido = "El servidor ya está corriendo."
                        }
                    }) {
                        Text("Iniciar servidor")
                    }
                }
            }
        }
    }
}
