package com.example.holamundo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.holamundo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var button:Button
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()

        Log.v("ciclo_vida","Ejecutando metodo OnCreate")
    }

    private fun acciones() {

        binding.botonSaludar.setOnClickListener {
            var snackbar = Snackbar.make(it,
                "Saludo: " + resources.getString(R.string.saludo_snackbar),
                Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("Ocultar"){
                snackbar.dismiss()
            }
            snackbar.show()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.v("ciclo_vida","Ejecutando metodo OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("ciclo_vida","Ejecutando metodo OnResume")
    }
    override fun onRestart() {
        super.onRestart()
        Log.v("ciclo_vida","Ejecutando metodo OnRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.v("ciclo_vida","Ejecutando metodo OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("ciclo_vida","Ejecutando metodo OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("ciclo_vida","Ejecutando metodo OnDestroy")
    }

}