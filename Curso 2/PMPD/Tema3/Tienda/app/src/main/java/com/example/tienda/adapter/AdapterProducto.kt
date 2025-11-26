package com.example.tienda.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.databinding.ItemProductoBinding
import com.example.tienda.model.Producto
import com.google.android.material.snackbar.Snackbar

class AdapterProducto (var lista: ArrayList<Producto>, var contexto: Context): RecyclerView.Adapter<AdapterProducto.MyHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        var binding: ItemProductoBinding = ItemProductoBinding.inflate(LayoutInflater.from(contexto), parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val producto: Producto = lista[position] // o lista.get(position)
        holder.binding.nombreFila.text = producto.nombre
        holder.binding.btnDetalle.setOnClickListener {
            Snackbar.make(holder.binding.root,"El precio del articulo es ${producto.precio}",Snackbar.LENGTH_SHORT).show()

            // Arrancar otra pantalla y sacar los datos del producto

        }
        holder.binding.btnCompra.setOnClickListener {
            Snackbar.make(holder.binding.root,"El stock del articulo es ${producto.stock}",Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class MyHolder(var binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {
        // Aquí puedes inicializar las vistas del item

    }

}