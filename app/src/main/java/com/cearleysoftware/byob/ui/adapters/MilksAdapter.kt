package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemMilkBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.MilksData

//  Copyright © 2019 Cearley Software. All rights reserved.

class MilksAdapter(val list: List<MilksData>): RecyclerView.Adapter<MilksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemMilkBinding>(R.layout.item_milk)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.minusButton.setOnClickListener {
            updateMinus(item, position)
        }
        holder.binding.plusButton.setOnClickListener {
            updatePlus(item, position)
        }
    }

    private fun updatePlus(item: MilksData, position: Int) {
        if (item.count < 3){
            item.count ++
            notifyItemChanged(position)
        }
    }

    private fun updateMinus(item: MilksData, position: Int) {
        if (item.count > 0){
            item.count --
            notifyItemChanged(position)
        }
    }


    class ViewHolder(val binding: ItemMilkBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MilksData) {
            binding.milk = item
            binding.executePendingBindings()
        }

    }
}