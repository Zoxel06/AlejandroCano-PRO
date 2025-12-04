package com.example.tienda.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda.R
import com.example.tienda.databinding.ActivityDetalleBinding
import com.example.tienda.model.Producto

class DetalleActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetalleBinding
    lateinit var producto: Producto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        producto = intent.getSerializableExtra("producto") as Producto

        initGUI()

    }

    private fun initGUI() {
        binding.imagenProductoDetalle.setImageResource(R.mipmap.ic_launcher)
        binding.textoNombreDetalle.text = producto.nombre
        binding.textoPrecioDetalle.text = producto.precio.toString()
        binding.textoCategoriaDetalle.text = producto.categoria
        binding.textoDescripcionDetalle.text = producto.descripcion
    }
}