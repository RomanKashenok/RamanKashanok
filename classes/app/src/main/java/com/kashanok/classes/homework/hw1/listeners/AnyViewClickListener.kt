package com.kashanok.classes.homework.hw1.listeners

import android.view.View
import android.widget.TextView

class AnyViewClickListener(private val first: TextView, private val second: TextView) : View.OnClickListener {

    override fun onClick(view: View?) {
        val text = first.text
        val background = first.background

        first.background = second.background
        first.text = second.text
        second.background = background
        second.text = text
    }
}