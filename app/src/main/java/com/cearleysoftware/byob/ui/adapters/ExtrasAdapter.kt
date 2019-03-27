package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.databinding.ItemExtraBinding
import com.cearleysoftware.byob.extensions.inflateWithBinding
import com.cearleysoftware.byob.models.ExtrasData

class ExtrasAdapter: RecyclerView.Adapter<ExtrasAdapter.ViewHolder>() {

    private val extras:ArrayList<ExtrasData> = ArrayList()
    var lastSelectedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = parent.inflateWithBinding<ItemExtraBinding>(R.layout.item_extra)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = extras.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val extra = extras[position]
        holder.bind(extra)
        holder.binding.radioButton.setOnClickListener {
            if (lastSelectedIndex > -1 && position != lastSelectedIndex){
                val last = extras[lastSelectedIndex]
                last.isSelected = false
            }
            lastSelectedIndex = position
            extra.isSelected = true
            notifyDataSetChanged()
        }
    }

    fun updateList(list: ArrayList<ExtrasData>) {
        extras.clear()
        extras.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemExtraBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(extra: ExtrasData) {
            binding.extra = extra.extra
            binding.isSelected = extra.isSelected
            binding.executePendingBindings()
        }
    }
}