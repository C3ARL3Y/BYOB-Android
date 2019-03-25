package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemDrinkBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.Drink

class DrinkSearchAdapter: RecyclerView.Adapter<DrinkSearchAdapter.ViewHolder>() {

    private var drinks: ArrayList<Drink> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemDrinkBinding>(R.layout.item_drink)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = drinks.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink = drinks[position]
        holder.bind(drink)
    }

    fun updateData(data: List<Drink>){
        drinks.clear()
        drinks.addAll(data)
        notifyDataSetChanged()
    }

    fun getSongForPosition(position: Int): Drink {
        return drinks[position]
    }

    class ViewHolder constructor(
            private val binding: ItemDrinkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(drink: Drink) {
            binding.drink = drink
            binding.executePendingBindings()
        }
    }
}