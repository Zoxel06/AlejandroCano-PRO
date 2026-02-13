package com.example.agendajson

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.agendajson.adapter.UserAdapter
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.model.User
import com.example.agendajson.ui.dialog.DialogFilter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity : AppCompatActivity(),
    DialogFilter.OnDialogoFiltrarListener,
    UserAdapter.OnUserFavListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val urlBase = "https://dummyjson.com/users"

    private val favoritos = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this, this)
        setSupportActionBar(binding.toolbar)

        binding.recyclerUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerUsers.adapter = adapter

        cargarFavoritos()
        realizarPeticionJSON(urlBase)
    }

    private fun cargarFavoritos() {
        val prefs = getSharedPreferences("favs", Context.MODE_PRIVATE)
        val json = prefs.getString("lista", null) ?: return

        val type = object : TypeToken<ArrayList<User>>() {}.type
        favoritos.addAll(Gson().fromJson(json, type))
        actualizarFavoritosAdapter()
    }

    private fun guardarFavoritos() {
        val prefs = getSharedPreferences("favs", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("lista", Gson().toJson(favoritos))
            .apply()
    }

    private fun actualizarFavoritosAdapter() {
        val ids = favoritos.mapNotNull { it.id }
        adapter.setFavoritos(ids)
    }

    override fun onFavClicked(user: User) {
        if (favoritos.any { it.id == user.id }) {
            favoritos.removeAll { it.id == user.id }
        } else {
            favoritos.add(user)
        }
        guardarFavoritos()
        actualizarFavoritosAdapter()
    }

    private fun realizarPeticionJSON(url: String) {
        val request = JsonObjectRequest(
            url,
            {
                adapter.clearUsers()
                val gson = Gson()
                val usersArray: JSONArray = it.getJSONArray("users")

                for (i in 0 until usersArray.length()) {
                    val userJSON = usersArray.getJSONObject(i)
                    val user = gson.fromJson(userJSON.toString(), User::class.java)
                    adapter.addUser(user)
                }
            },
            {
                Log.e("VOLLEY", "Error en la petición")
            }
        )
        Volley.newRequestQueue(this).add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filtrar -> DialogFilter().show(supportFragmentManager, null)
            R.id.menu_favoritos -> mostrarFavoritos()
        }
        return true
    }

    private fun mostrarFavoritos() {
        adapter.clearUsers()
        favoritos.forEach { adapter.addUser(it) }
    }

    override fun onGeneroSelected(genero: String) {
        if (genero == "all") {
            realizarPeticionJSON(urlBase)
        } else {
            val url = "$urlBase/filter?key=gender&value=$genero"
            realizarPeticionJSON(url)
        }
    }
}
