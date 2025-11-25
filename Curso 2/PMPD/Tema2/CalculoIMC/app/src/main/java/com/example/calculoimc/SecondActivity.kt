package com.example.calculoimc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculoimc.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()

    }

    private fun acciones() {
        val IMC = intent.getFloatExtra("IMC", 0F)
        val categoria = intent.getStringExtra("categoria")

        val IMCint = IMC.toInt()

        val textViewIMC = findViewById<TextView>(R.id.resultadoIMC)
        val textViewCategoria = findViewById<TextView>(R.id.resultadoCategoria)


        textViewIMC.text = "Tu IMC es %.2f".format(IMC)
        textViewCategoria.text = categoria

        when {
            IMCint< 19 -> binding.resultadoImagen.setImageResource(R.drawable.estado1)
            IMCint in 19..25 -> binding.resultadoImagen.setImageResource(R.drawable.estado2)
            IMCint in 26..30 -> binding.resultadoImagen.setImageResource(R.drawable.estado3)
            IMCint in 31..35 -> binding.resultadoImagen.setImageResource(R.drawable.estado4)
            IMCint in 36..40 -> binding.resultadoImagen.setImageResource(R.drawable.estado5)
            IMCint > 40 -> binding.resultadoImagen.setImageResource(R.drawable.estado6)

            else -> "Error"
        }
        binding.resultadoImagen

    }


}