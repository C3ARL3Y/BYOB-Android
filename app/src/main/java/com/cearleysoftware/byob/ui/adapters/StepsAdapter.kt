package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemStepBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding

//  Copyright © 2019 Cearley Software. All rights reserved.

class StepsAdapter(private val steps: List<String>): RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemStepBinding>(R.layout.item_step)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = steps.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = steps[position]
        holder.bind(step)
    }

    fun getStepForPosition(position: Int): String{
        val step = steps[position]
        return step
    }

    class ViewHolder constructor(
            private val binding: ItemStepBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(step: String) {
            binding.step = step
            binding.executePendingBindings()
        }
    }
}