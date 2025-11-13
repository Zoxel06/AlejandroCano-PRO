package com.example.concesionario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.concesionario.databinding.ActivityMainBinding
import com.example.concesionario.model.Marca
import com.example.concesionario.ui.SecondActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var listaMarcas: ArrayList<Marca>
    lateinit var adapterMarcas: ArrayAdapter<Marca>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()
        initGUI()
        acciones()
    }

    private fun initGUI() {
        binding.spinnerMarcas.adapter = adapterMarcas // Para juntar la parte del spinner con el adapter
        adapterMarcas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Para que cuando los items del spinner esten desplegados se vean mejor
    }

    private fun instancias() {
        listaMarcas = arrayListOf(
            Marca(
                "Ford", R.drawable.ford
            ),
            Marca("Opel", R.drawable.opel),
            Marca("Mercedes", R.drawable.opel),
            Marca("Fiat", R.drawable.fiat)
        )

        adapterMarcas =
            ArrayAdapter(this,android.R.layout.simple_spinner_item,listaMarcas)
    }

    private fun acciones() {
        binding.botonFiltrar.setOnClickListener {
            /*
            Snackbar.make(
                it,
                "La seleccion del spinner de vehiculos es " +
                        "${binding.spinnerMarcas.adapter.getItem(binding.spinnerMarcas.selectedItemPosition)}",
                Snackbar.LENGTH_SHORT
            )
             */
            val intent = Intent(this, SecondActivity::class.java) // Para poder cambiar de una pantalla a otra al darle al boton
            intent.putExtra("vehiculo",binding.spinnerVehiculos.selectedItem.toString()) // Para pasar los datos del spinner de vehiculos
            intent.putExtra("marca",
                binding.spinnerMarcas.adapter.getItem(binding.spinnerMarcas.selectedItemPosition)as Marca) // Para pasar los datos del spinner de marcas
            startActivity(intent)
        }
        binding.spinnerVehiculos.onItemSelectedListener = this
        binding.spinnerMarcas.onItemSelectedListener = this
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        when(parent?.id){
            binding.spinnerVehiculos.id ->{
                Snackbar.make(
                    view!!,
                    "La seleccion del spinner de vehiculos es ${parent.adapter.getItem(position)}",
                    Snackbar.LENGTH_SHORT
                ).show()

            }

            binding.spinnerMarcas.id -> {
                /*
                Snackbar.make(
                    view!!,
                    "La seleccion del spinner de vehiculos es ${(binding.spinnerMarcas.adapter.getItem(position) as Marca).nombre}",
                    Snackbar.LENGTH_SHORT
                )
                 */

                val marcaSeleccionada: Marca = parent.adapter.getItem(position) as Marca
                binding.imagenMarca.setImageResource(marcaSeleccionada.imagen)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}