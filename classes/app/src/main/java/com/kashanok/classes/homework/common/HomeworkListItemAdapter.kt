package com.kashanok.classes.homework.common

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kashanok.classes.R
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.BaseItemClickListener
import com.kashanok.classes.homework.common.workitem.WorkNumber

class HomeworkListItemAdapter(
    private val itemClickListener: BaseItemClickListener<BaseAdapterItem<WorkNumber>>,
    private val context: Context
) : RecyclerView.Adapter<HomeworkItemViewHolder>() {

    companion object {
        const val HOMEWORK_ITEM = 0
    }

    var items = mutableListOf<BaseAdapterItem<WorkNumber>>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkItemViewHolder {
        return HomeworkItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_homework_item, parent, false))
    }

    override fun onBindViewHolder(holder: HomeworkItemViewHolder, position: Int) {
        holder.title.text = context.getString(items[position].model.titleId)
        holder.description.text = context.getString(items[position].model.descId)
        holder.picture.setImageResource(items[position].model.imageResourceId)

        holder.workArea.setOnClickListener {
            itemClickListener.onItemClicked(items[position], position)
        }
    }

    fun setNewItems(itemsList: List<BaseAdapterItem<WorkNumber>>) {
        items = itemsList.toMutableList()
        notifyDataSetChanged()
    }
}