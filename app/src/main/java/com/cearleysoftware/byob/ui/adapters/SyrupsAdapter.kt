package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemSyrupBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.SyrupsData

//  Copyright © 2019 Cearley Software. All rights reserved.

class SyrupsAdapter(val list: List<SyrupsData>): RecyclerView.Adapter<SyrupsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemSyrupBinding>(R.layout.item_syrup)
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

    private fun updatePlus(item: SyrupsData, position: Int) {
        item.count ++
        notifyItemChanged(position)
    }

    private fun updateMinus(item: SyrupsData, position: Int) {
        if (item.count > 0){
            item.count --
            notifyItemChanged(position)
        }
    }


    class ViewHolder(val binding: ItemSyrupBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SyrupsData) {
            binding.syrup = item
            binding.executePendingBindings()
        }

    }
}