package com.example.tablasmultiplicarandroid;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.tablasmultiplicarandroid.ui.theme.TablasMultiplicarAndroidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TablasMultiplicarAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    var resultado by remember { mutableStateOf(TextFieldValue()) }

    Column {
        Button(onClick = {
            resultado = tablamulti()
        }) {
            Text("Hacer tablas de multiplicar")
        }
        TextField(value = resultado, onValueChange = { /* No hacemos nada al cambiar el valor */ }, modifier = Modifier.fillMaxSize())
    }
}

private fun tablamulti(): TextFieldValue {
    return runBlocking {
        val tablaInicio = 1
        val tablaFin = 10

        val deferredList = (tablaInicio..tablaFin).map { tabla ->
            async(Dispatchers.Default) {
                buildString {
                    append("Tabla del $tabla:\n")
                    for (i in 1..10) {
                        append("$tabla x $i = ${tabla * i}\n")
                        delay(100)
                    }
                    append("Tabla del $tabla completada\n")
                }
            }
        }

        val resultList = deferredList.awaitAll()
        TextFieldValue(text = resultList.joinToString("\n"))
    }
}