package com.example.tablasmultiplicarandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tablasmultiplicarandroid.ui.theme.TablasMultiplicarAndroidTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TablasMultiplicarAndroidTheme {
                MultiplicationApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiplicationApp() {
    var number by remember { mutableStateOf("") }
    var table by remember { mutableStateOf<List<String>>(emptyList()) }
    var visibleItems by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tablas de multiplicar", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Introduce un número para ver su tabla de multiplicar:",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = number,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) number = input
                },
                label = { Text("Número") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val n = number.toIntOrNull()
                    if (n != null) {
                        table = (1..10).map { "$n × $it = ${n * it}" }
                        visibleItems = 0
                    } else {
                        table = emptyList()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = number.isNotEmpty()
            ) {
                Text("Generar tabla")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (table.isNotEmpty()) {
                Text(
                    text = "Tabla del ${number.toInt()}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(table) { index, item ->
                        // Animar la aparición progresiva de cada línea
                        LaunchedEffect(table) {
                            delay(index * 100L)
                            visibleItems = index + 1
                        }

                        AnimatedVisibility(
                            visible = index < visibleItems,
                            enter = fadeIn(animationSpec = tween(400)) +
                                    slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(400))
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Text(
                                    text = item,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "Aún no has generado ninguna tabla.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}