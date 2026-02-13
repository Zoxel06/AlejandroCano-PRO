package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.databinding.ItemUserCardBinding
import com.example.agendajson.model.User

class UserAdapter(
    private val context: Context,
    private val listener: OnUserFavListener
) : RecyclerView.Adapter<UserAdapter.MyHolder>() {

    private val lista = ArrayList<User>()
    private val favoritos = ArrayList<Long>()

    interface OnUserFavListener {
        fun onFavClicked(user: User)
    }

    inner class MyHolder(val binding: ItemUserCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setFavoritos(ids: List<Long>) {
        favoritos.clear()
        favoritos.addAll(ids)
        notifyDataSetChanged()
    }

    fun clearUsers() {
        lista.clear()
        notifyDataSetChanged()
    }

    fun addUser(user: User) {
        lista.add(user)
        notifyItemInserted(lista.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemUserCardBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val user = lista[position]

        holder.binding.textoCard.text = user.email
        holder.binding.toolbarCard.title = user.firstName

        Glide.with(context)
            .load(user.image)
            .into(holder.binding.imagenCard)

        // Color simple para favorito
        val esFav = favoritos.contains(user.id)
        holder.binding.imgFav.setBackgroundColor(
            if (esFav) android.graphics.Color.YELLOW
            else android.graphics.Color.LTGRAY
        )

        holder.binding.imgFav.setOnClickListener {
            listener.onFavClicked(user)
        }
    }

    override fun getItemCount(): Int = lista.size
}
