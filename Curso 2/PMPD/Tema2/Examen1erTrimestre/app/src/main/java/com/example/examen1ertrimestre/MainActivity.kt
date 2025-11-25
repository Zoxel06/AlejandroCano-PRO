package com.example.examen1ertrimestre

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examen1ertrimestre.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()
        acciones()

    }

    private fun instancias() {
        val listaCombustible = arrayOf("Diesel", "Gasolina", "Hibrido", "Electrico")
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaCombustible)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGasolina.setAdapter(adapter)
        binding.textoAutonomia.isEnabled = false

    }

    private fun acciones() {

        binding.spinnerGasolina.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (binding.spinnerGasolina.selectedItem.toString() == "Hibrido" || binding.spinnerGasolina.selectedItem.toString() == "Electrico") {
                        binding.textoAnioMatriculacion.setText("2018")
                        binding.textoAnioMatriculacion.isEnabled = false
                    } else {
                        binding.textoAnioMatriculacion.isEnabled = true
                    }

                    if (binding.spinnerGasolina.selectedItem.toString() == "Hibrido") {
                        binding.textoAutonomia.isEnabled = true
                    } else {
                        binding.textoAutonomia.setText("")
                        binding.textoAutonomia.isEnabled = false
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }


        binding.botonEnviar.setOnClickListener {

            if (binding.textoNombre.text.isBlank() || binding.textoApellidos.text.isBlank() ||
                binding.textoAnioMatriculacion.text.isBlank()
            ) {
                Snackbar.make(
                    binding.root,
                    "Por favor, rellena todos los campos",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                if (binding.spinnerGasolina.selectedItem.toString() == "Hibrido") {
                    binding.textoAnioMatriculacion.setText("2018")
                    binding.textoAnioMatriculacion.isEnabled = false
                }
                Snackbar.make(binding.root, "Coche registrado con exito", Snackbar.LENGTH_SHORT)
                    .show()


                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("nombre", binding.textoNombre.text.toString())
                intent.putExtra("apellidos", binding.textoApellidos.text.toString())
                intent.putExtra("anioMatriculacion", binding.textoAnioMatriculacion.text.toString())
                intent.putExtra("combustible", binding.spinnerGasolina.selectedItem.toString())
                intent.putExtra("autonomia", binding.textoAutonomia.text.toString())
                startActivity(intent)

            }

        }

    }
}