package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemFavoriteBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.CustomDrink

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var favorites: List<CustomDrink> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemFavoriteBinding>(R.layout.item_favorite)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = favorites.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.bind(favorite.name)
    }

    fun updateData(data: List<CustomDrink>){
        favorites = data
        notifyDataSetChanged()
    }

    fun getFavoriteForPosition(position: Int): CustomDrink {
        return favorites[position]
    }

    class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(favoriteName: String){
            binding.name = favoriteName
            binding.executePendingBindings()
        }
    }
}