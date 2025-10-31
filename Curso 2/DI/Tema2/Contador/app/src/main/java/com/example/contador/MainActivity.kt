package com.example.contador

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var contador: Int = 0
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contador = savedInstanceState?.getInt("tag_contador") ?: 0
        binding.textoContador?.text = "$contador" // o contador.toString

        acciones()
    }

    private fun acciones() {
        binding.botonIncremento?.setOnClickListener(this)
        binding.botonDecremento?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Diferenciar quien ha lanzado el evento

        when (v!!.id) {
            binding.botonIncremento!!.id -> {
                contador++
            }

            binding.botonDecremento!!.id -> {
                contador--
            }
        }

        binding.textoContador?.text = "$contador"

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("tag_contador",contador)
    }
}