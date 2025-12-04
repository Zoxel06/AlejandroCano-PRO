package com.example.tienda.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.databinding.CarritoDetalleBinding
import com.example.tienda.dataset.DataSet

class DetalleCarrito : AppCompatActivity(), AdapterProducto.OnProductoCarritoListener {

    private lateinit var binding: CarritoDetalleBinding
    private lateinit var adapterCarrito: AdapterProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CarritoDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarCarrito)
        supportActionBar?.title = "Carrito"

        adapterCarrito = AdapterProducto(DataSet.Companion.listaCarrito, this)
        binding.recyclerCarrito.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrito.adapter = adapterCarrito

        actualizarContadorCarrito()
    }

    override fun actualizarContadorCarrito() {
        binding.textoContador.text = DataSet.Companion.listaCarrito.size.toString()
        adapterCarrito.notifyDataSetChanged()
    }
}