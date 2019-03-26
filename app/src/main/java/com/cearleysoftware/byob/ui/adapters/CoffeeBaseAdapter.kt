package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemCoffeeBaseBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.ui.fragments.customize.CoffeeBaseFragment

//  Copyright © 2019 Cearley Software. All rights reserved.

class CoffeeBaseAdapter: RecyclerView.Adapter<CoffeeBaseAdapter.ViewHolder>() {

    private val bases:ArrayList<CoffeeBaseFragment.CoffeeBaseData> = ArrayList()

    var lastSelectedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemCoffeeBaseBinding>(R.layout.item_coffee_base)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = bases.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baseData = bases[position]
        holder.bind(baseData)
        holder.binding.radioButton.setOnClickListener {
            if (lastSelectedIndex > -1 && position != lastSelectedIndex){
                val last = bases[lastSelectedIndex]
                last.isSelected = false
            }
            lastSelectedIndex = position
            baseData.isSelected = true
            notifyDataSetChanged()
        }
    }

    fun updateList(list: List<CoffeeBaseFragment.CoffeeBaseData>){
        bases.clear()
        bases.addAll(list)
        notifyDataSetChanged()
    }


    class ViewHolder constructor(
            val binding: ItemCoffeeBaseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CoffeeBaseFragment.CoffeeBaseData) {
            binding.base = data.base
            binding.isSelected = data.isSelected
            binding.executePendingBindings()
        }
    }
}