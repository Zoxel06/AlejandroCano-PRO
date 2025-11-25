package com.example.examen1ertrimestre

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1ertrimestre.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()

    }

    private fun acciones() {
        val nombre = intent.getStringExtra("nombre")
        val apellidos = intent.getStringExtra("apellidos")
        val anioMatriculacion = intent.getStringExtra("anioMatriculacion")
        val combustible = intent.getStringExtra("combustible")
        val autonomia = intent.getStringExtra("autonomia")

        val textViewNombre = findViewById<TextView>(R.id.resultadoNombre)
        val textViewApellidos = findViewById<TextView>(R.id.resultadoApellidos)
        val textViewAnioMatriculacion = findViewById<TextView>(R.id.resultadoAnioMatriculacion)
        val textViewCombustible = findViewById<TextView>(R.id.resultadoGasolina)
        val textViewAutonomia = findViewById<TextView>(R.id.resultadoAutonomia)


        textViewNombre.text = nombre
        textViewApellidos.text = apellidos
        textViewAnioMatriculacion.text = anioMatriculacion
        textViewCombustible.text = combustible
        textViewAutonomia.text = autonomia


        if (textViewCombustible.text.toString() == "Electrico") {
            binding.imagenEtiqueta.setImageResource(R.drawable.etiqueta0)
        } else if (textViewCombustible.text.toString() == "Hibrido") {
            if (textViewAutonomia.text.toString().toInt() > 80) {
                binding.imagenEtiqueta.setImageResource(R.drawable.etiqueta0)
            } else {
                binding.imagenEtiqueta.setImageResource(R.drawable.etiquetaeco)
            }
        } else if (textViewCombustible.text.toString() == "Diesel" && textViewAnioMatriculacion.text.toString()
                .toInt() <= 2015 && textViewAnioMatriculacion.text.toString().toInt() >= 2006
        ) {
            binding.imagenEtiqueta.setImageResource(R.drawable.etiquetac)
        } else if (textViewCombustible.text.toString() == "Gasolina" && textViewAnioMatriculacion.text.toString()
                .toInt() <= 2017 && textViewAnioMatriculacion.text.toString().toInt() >= 2008
        ) {
            binding.imagenEtiqueta.setImageResource(R.drawable.etiquetac)
        } else {
            binding.imagenEtiqueta.setImageResource(R.drawable.etiquetab)
        }

    }
}