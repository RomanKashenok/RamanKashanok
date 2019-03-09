package com.kashanok.classes.classwork.cw6

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.common.ImageLoader
import kotlinx.android.synthetic.main.item_student_list.view.*

class Cw6RecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(studentItem: BaseAdapterItem<Cw6StudentItem>, position: Int) {
        view.imageView.setImageDrawable(null)
        ImageLoader.clearArea(view.imageView)
        ImageLoader.loadImage(view.imageView, studentItem.model.imageUrl)
        view.textView.text = studentItem.model.name
    }
}