package com.kashanok.classes.homework.common

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.recycler_homework_item.view.*

class HomeworkItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title = view.title
    val description = view.description
    val picture = view.hwIcon
    val workArea = view.workArea

}