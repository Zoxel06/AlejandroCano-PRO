package com.example.calculoimc

import android.content.Intent
import  android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculoimc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()
        initGUI()
        acciones()
    }

    private fun initGUI() {

    }

    private fun instancias() {

    }

    private fun acciones() {
        binding.botonCalculo.setOnClickListener { botonCalculoIMC() }
    }



    private fun botonCalculoIMC() {

        val pesoUsuario = binding.textoPeso.text.toString()
        val alturaUsuario = binding.textoAltura.text.toString()

        if (binding.textoPeso.text.isBlank() || binding.textoAltura.text.isBlank()) {
            Snackbar.make(binding.root, "Rellena todos los campos", Snackbar.LENGTH_SHORT).show()
        } else {
            if (binding.botonMasculino.isChecked == false && binding.botonFemenino.isChecked == false) {
                Snackbar.make(binding.root, "Selecciona un genero", Snackbar.LENGTH_SHORT).show()
            } else {
                // Logica del programa

                val IMC =
                    (pesoUsuario.toFloat() / (alturaUsuario.toFloat() * alturaUsuario.toFloat())) * 10000f
                val IMCint = IMC.toInt()

                var categoria = ""

                if (binding.botonMasculino.isChecked) {
                    categoria = when {

                        IMCint < 19 -> "Bajo peso"
                        IMCint in 19..25 -> "Peso normal"
                        IMCint in 26..30 -> "Pre-obesidad o Sobrepeso"
                        IMCint in 31..35 -> "Obesidad clase I"
                        IMCint in 36..40 -> "Obesidad clase II"
                        IMCint > 40 -> "Obesidad clase III"

                        else -> "Error"
                    }
                } else if (binding.botonFemenino.isChecked) {
                    categoria = when {

                        IMCint < 17 -> "Bajo peso"
                        IMCint in 17..23 -> "Peso normal"
                        IMCint in 24..26 -> "Pre-obesidad o Sobrepeso"
                        IMCint in 27..31 -> "Obesidad clase I"
                        IMCint in 32..34 -> "Obesidad clase II"
                        IMCint > 34 -> "Obesidad clase III"

                        else -> "Error"
                    }
                }

                binding.textoPeso.text.clear()
                binding.textoAltura.text.clear()
                binding.botonMasculino.isChecked = false
                binding.botonFemenino.isChecked = false



                val intent = Intent(this, SecondActivity::class.java)

                intent.putExtra("IMC",IMC)
                intent.putExtra("categoria",categoria)

                startActivity(intent)

            }
        }

    }





}