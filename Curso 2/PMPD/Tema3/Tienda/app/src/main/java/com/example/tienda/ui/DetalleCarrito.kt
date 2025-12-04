package com.example.tienda.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.dataset.DataSet
import com.example.tienda.databinding.CarritoDetalleBinding

class DetalleCarrito : AppCompatActivity(), AdapterProducto.OnProductoCarritoListener {

    private lateinit var binding: CarritoDetalleBinding
    private lateinit var adapterCarrito: AdapterProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CarritoDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarCarrito)
        supportActionBar?.title = "Carrito"

        adapterCarrito = AdapterProducto(DataSet.listaCarrito, this)
        binding.recyclerCarrito.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrito.adapter = adapterCarrito

        actualizarContadorCarrito()
    }

    override fun actualizarContadorCarrito() {
        binding.textoContador.text = DataSet.listaCarrito.size.toString()
        adapterCarrito.notifyDataSetChanged()
    }
}
