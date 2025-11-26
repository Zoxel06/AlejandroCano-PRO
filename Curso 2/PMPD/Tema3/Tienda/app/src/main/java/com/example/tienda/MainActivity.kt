package com.example.tienda

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
import com.example.tienda.model.Producto

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterProducto: AdapterProducto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista: ArrayList<Producto> =
            arrayListOf(
            (Producto(1,"Nombre1",112,10.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre2",212,20.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre3",312,30.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre4",412,40.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre5",512,50.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre6",612,60.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre7",712,70.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre8",812,80.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre9",912,90.0,"descripcion","categoria","imagen")),
            (Producto(1,"Nombre10",1012,100.0,"descripcion","categoria","imagen")),
            )

        adapterProducto = AdapterProducto(lista,this)

        if (resources.configuration.orientation == 1){
            binding.recyclerProductos.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false)
        } else if (resources.configuration.orientation == 2){
            binding.recyclerProductos.layoutManager = GridLayoutManager(this,3,
                LinearLayoutManager.VERTICAL,false)
        }

        binding.recyclerProductos.adapter = adapterProducto



    }
}