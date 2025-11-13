package com.example.concesionario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.concesionario.model.Vehiculo

class AdapterModelos(var lista: ArrayList<Vehiculo>, var contexto: Context): BaseAdapter() {

    // El numero de elementos que se pintaran en el spinner
    override fun getCount(): Int {
        return lista.size
    }

    // Retorna el elemento ubicado en una posicion
    override fun getItem(position: Int): Any? {
        return lista[position]
    }

    // Retorna el id del elemento ubicado en una posicion
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Indica como se representa graficamente un elemento ubicado en una posicion concreta
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        // 1. Traer el XML para utilizarlo aqui
        val view: View = LayoutInflater.from(contexto).inflate(
            R.layout.item_spinner,
            parent ,
            false)

        // 2. Sacar cada uno de los elementos del XML
        val imagenMarca: ImageView = view.findViewById(R.id.itemMarca)
        val imagenModelo: ImageView = view.findViewById(R.id.itemModelo)
        val textoModelo: TextView = view.findViewById(R.id.itemNombre)

        // 3. Rellenar cada uno de los elementos del paso
        val modelo = lista[position]
        imagenMarca.setImageResource(modelo.marca.imagen)
        imagenModelo.setImageResource(modelo.imagen)
        textoModelo.setText(modelo.modelo)

        // 4. Retornar la vista del paso 1
        return view

    }
}