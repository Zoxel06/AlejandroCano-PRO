package com.example.tienda.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tienda.R
import com.example.tienda.databinding.ItemProductoBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto
import com.example.tienda.ui.DetalleActivity
import com.google.android.material.snackbar.Snackbar

class AdapterProducto(var lista: ArrayList<Producto>, var contexto: Context) :
    RecyclerView.Adapter<AdapterProducto.MyHolder>() {

    lateinit var listener: OnProductoCarritoListener

    init {
        listener = contexto as OnProductoCarritoListener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        var binding: ItemProductoBinding =
            ItemProductoBinding.inflate(LayoutInflater.from(contexto), parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {


        val producto: Producto = lista[position] // o lista.get(position)

        Glide.with(contexto)
            .load(producto.imagen)
            .placeholder(R.drawable.producto)
            .into(holder.binding.imagenFila)

        holder.binding.nombreFila.text = producto.nombre
        holder.binding.btnDetalle.setOnClickListener {
            Snackbar.make(
                holder.binding.root,
                "El precio del articulo es ${producto.precio}",
                Snackbar.LENGTH_SHORT
            ).show()

            // Arrancar otra pantalla y sacar los datos del producto

        }
        holder.binding.btnCompra.setOnClickListener {
            Snackbar.make(
                holder.binding.root,
                "El stock del articulo es ${producto.stock}",
                Snackbar.LENGTH_SHORT
            ).show()

            DataSet.addProducto(producto)

        }

        holder.binding.btnDetalle.setOnClickListener {
            val intent = Intent(contexto, DetalleActivity::class.java)
            intent.putExtra("producto", producto)
            contexto.startActivity(intent)

            listener.actualizarContadorCarrito()

        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class MyHolder(var binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {
        // Aquí puedes inicializar las vistas del item

    }

    interface OnProductoCarritoListener {

        fun actualizarContadorCarrito(): Unit

    }

}