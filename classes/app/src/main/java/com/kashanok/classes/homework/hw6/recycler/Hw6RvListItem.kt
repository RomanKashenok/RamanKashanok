package com.kashanok.classes.homework.hw6.recycler

import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.hw6.Student

class Hw6RvListItem(item: Student) : BaseAdapterItem<Student>(item) {
    var imageUrl: String? = null
}