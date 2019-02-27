package com.kashanok.classes.common

abstract class BaseAdapterItem<T>(val model: T) {

    companion object {
        private const val DEFAULT_ITEM_TYPE = 0
    }

    open fun getItemType(): Int = DEFAULT_ITEM_TYPE
}