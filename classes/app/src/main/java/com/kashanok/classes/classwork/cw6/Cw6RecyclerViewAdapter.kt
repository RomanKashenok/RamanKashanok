package com.kashanok.classes.classwork.cw6

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem

class Cw6RecyclerViewAdapter : RecyclerView.Adapter <Cw6RecyclerViewHolder>() {

    private var items = arrayListOf<BaseAdapterItem<Cw6StudentItem>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Cw6RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student_list, parent, false)
        val holder = Cw6RecyclerViewHolder(view)

        holder.itemView.setOnClickListener { Toast.makeText(it.context, "item # ${holder.adapterPosition} clicked", Toast.LENGTH_LONG).show() }

        return holder
    }

    override fun onBindViewHolder(holder: Cw6RecyclerViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setNewItems(newItems: List<BaseAdapterItem<Cw6StudentItem>>) {
        items = newItems as ArrayList<BaseAdapterItem<Cw6StudentItem>>
        notifyDataSetChanged()
    }
}
