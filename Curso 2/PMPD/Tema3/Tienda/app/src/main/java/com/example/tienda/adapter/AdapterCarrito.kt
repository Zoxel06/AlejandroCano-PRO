package com.example.tienda.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tienda.databinding.ItemCarritoBinding
import com.example.tienda.model.Producto

class AdapterCarrito(var lista: ArrayList<Producto>, var contexto: Context) : RecyclerView.Adapter<AdapterCarrito.MyHolder>() {

    inner class MyHolder(binding: ItemCarritoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding: ItemCarritoBinding = ItemCarritoBinding.inflate(LayoutInflater.from(contexto),parent,false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val producto = lista[position]
        Glide.with(contexto).load(producto.imagen)
    }

    override fun getItemCount(): Int {
        return lista.size
    }



}