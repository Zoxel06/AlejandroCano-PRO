package com.example.tienda

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.databinding.ActivityMainBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto

class MainActivity : AppCompatActivity(), AdapterProducto.OnProductoCarritoListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterProducto: AdapterProducto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista: ArrayList<Producto> = DataSet.lista

        adapterProducto = AdapterProducto(lista, this)

        if (resources.configuration.orientation == 1) {
            binding.recyclerProductos.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false
            )
        } else if (resources.configuration.orientation == 2) {
            binding.recyclerProductos.layoutManager = GridLayoutManager(
                this, 2,
                LinearLayoutManager.VERTICAL, false
            )
        }

        binding.recyclerProductos.adapter = adapterProducto

    }

    override fun actualizarContadorCarrito() {
        binding.textoContador.text = DataSet.listaCarrito.size.toString()
    }
}