package com.example.controlarmovil

import android.graphics.BitmapFactory
import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.DataInputStream
import java.net.ServerSocket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var startServerButton: Button
    private lateinit var imageView: ImageView
    private lateinit var messageTextView: TextView

    private val PORT = 9000  // Debe coincidir con el de Python

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServerButton = findViewById(R.id.startServerButton)
        imageView = findViewById(R.id.imageView)
        messageTextView = findViewById(R.id.messageTextView)

        startServerButton.setOnClickListener {
            startServerButton.isEnabled = false
            startServerButton.text = "Servidor activo"
            startServer()
        }
    }

    private fun startServer() {
        thread {
            try {
                val server = ServerSocket(PORT)
                while (true) {
                    val client = server.accept()
                    val input = DataInputStream(client.getInputStream())

                    // Leemos los primeros 4 bytes: tamaÃ±o de la imagen
                    val size = input.readInt()
                    val buffer = ByteArray(size)
                    input.readFully(buffer)  // Leemos exactamente size bytes

                    // Convertimos a bitmap
                    val bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.size)

                    // Mostramos en el ImageView
                    runOnUiThread {
                        imageView.setImageBitmap(bitmap)
                        messageTextView.text = "Captura recibida"
                    }

                    client.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    messageTextView.text = "Error en servidor: ${e.message}"
                }
            }
        }
    }
}