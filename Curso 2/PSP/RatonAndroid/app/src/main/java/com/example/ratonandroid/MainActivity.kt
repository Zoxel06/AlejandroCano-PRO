package com.example.ratonandroid

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ratonandroid.ui.theme.RatonAndroidTheme
import java.net.Socket

class MainActivity : ComponentActivity() {
    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RatonAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    Posicion(context)
                }
            }
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        registerAccelerometerListener()
    }

    private fun registerAccelerometerListener() {
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val accelerometerListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val direction = determineDirection(x, y, z)
                if (direction != "quieto") {
                    sendDirectionToServer(direction)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // No es relevante para este ejemplo
            }
        }

        sensorManager.registerListener(
            accelerometerListener,
            accelerometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun determineDirection(x: Float, y: Float, z: Float): String {
        return when {
            x > 2 -> "izquierda"
            x < -2 -> "derecha"
            y > 2 -> "abajo"
            y < -2 -> "arriba"
            else -> "quieto"
        }
    }

    private fun sendDirectionToServer(direction: String) {
        Thread {
            val host = "192.168.2.115" // Cambia esta dirección por la del servidor
            val puerto = 6000 // Puerto del servidor

            try {
                val cliente = Socket(host, puerto)
                val outputStream = cliente.getOutputStream()

                val mensaje = "$direction"
                outputStream.write(mensaje.toByteArray())
                outputStream.flush()

                cliente.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Posicion(context: Context) {
    Toast.makeText(context, "Movimiento detectado", Toast.LENGTH_SHORT).show()
}