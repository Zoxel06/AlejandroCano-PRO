package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

    // 🎨 Fondo con degradado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tablas de multiplicar", fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            containerColor = Color.Transparent // 👈 deja ver el degradado
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

                // 🔘 Animación de escala del botón
                val scale by animateFloatAsState(
                    targetValue = if (table.isNotEmpty()) 1.05f else 1f,
                    animationSpec = tween(500)
                )

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer(scaleX = scale, scaleY = scale),
                    enabled = number.isNotEmpty()
                ) {
                    Text("Generar tabla")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (table.isNotEmpty()) {
                    // 🌀 Título animado
                    AnimatedVisibility(
                        visible = table.isNotEmpty(),
                        enter = fadeIn(animationSpec = tween(600)) +
                                slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(600))
                    ) {
                        Text(
                            text = "Tabla del ${number.toInt()}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemsIndexed(table) { index, item ->
                            LaunchedEffect(table) {
                                delay(index * 100L)
                                visibleItems = index + 1
                            }

                            AnimatedVisibility(
                                visible = index < visibleItems,
                                enter = fadeIn(animationSpec = tween(400)) +
                                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(400))
                            ) {
                                // 💎 Tarjeta más estilizada
                                Card(
                                    modifier = Modifier
                                        .padding(vertical = 6.dp)
                                        .fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                                    ),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                    shape = MaterialTheme.shapes.extraLarge
                                ) {
                                    Text(
                                        text = item,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(16.dp)
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
}
