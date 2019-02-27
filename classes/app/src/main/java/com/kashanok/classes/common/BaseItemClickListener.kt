package com.kashanok.classes.common

interface BaseItemClickListener<T> {

    companion object {
        const val DEFAULT_CLICK_TYPE_SINGLE = 0
    }

    fun onItemClicked(model: T, position: Int, clickType: Int = DEFAULT_CLICK_TYPE_SINGLE)
}