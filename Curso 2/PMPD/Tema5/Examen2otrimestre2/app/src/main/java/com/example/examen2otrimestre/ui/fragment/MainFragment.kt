package com.example.examen2otrimestre.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.examen2otrimestre.CardsAdapter
import com.example.examen2otrimestre.databinding.FragmentMainBinding
import com.example.examen2otrimestre.model.Lanzamiento
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private lateinit var listaCartas: ArrayList<Lanzamiento>
    private lateinit var adapter: CardsAdapter
    private lateinit var database: FirebaseDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listaCartas = ArrayList()
        database = FirebaseDatabase.getInstance("https://accexamences-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        database.getReference("lanzamientos").get().addOnSuccessListener {
            val json = it.value as String
            val gson = Gson()
            val lanzamientos = gson.fromJson(json, Array<Lanzamiento>::class.java).toList()
            listaCartas.addAll(lanzamientos)
        }

        adapter = CardsAdapter(this, listaCartas)

        binding.recyclerLanzamientos.adapter = adapter


    }

}