package com.example.examen2otrimestre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2otrimestre.model.Lanzamiento
import com.example.examen2otrimestre.ui.fragment.MainFragment

class CardsAdapter(var contexto: MainFragment, var listaDatos: List<*>):
    RecyclerView.Adapter<CardsAdapter.MyHolder>() {

    class MyHolder ( var view: View): RecyclerView.ViewHolder(view) {

        lateinit var nombre: TextView



        init {
            nombre = view.findViewById(R.id.nombre_item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {


        var layoutInflater: LayoutInflater = LayoutInflater.from(contexto.requireContext())


        var view: View = layoutInflater.inflate(R.layout.fragment_main, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var lanzamiento: Lanzamiento = listaDatos.get(position) as Lanzamiento
        holder.nombre.setText(lanzamiento.details)
    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }

}