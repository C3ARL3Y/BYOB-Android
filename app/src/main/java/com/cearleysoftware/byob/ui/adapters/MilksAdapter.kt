package com.cearleysoftware.byob.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cearleysoftware.byob.databinding.ItemMilkBinding
import com.cearleysoftware.byob.models.MilksData

class MilksAdapter(private val list: List<MilksData>): RecyclerView.Adapter<MilksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(private val binding: ItemMilkBinding): RecyclerView.ViewHolder(binding.root){

    }
}