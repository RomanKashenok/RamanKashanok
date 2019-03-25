package com.kashanok.classes.homework.hw7.recycler

import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.hw7.Student

class Hw7RvListItem(item: Student) : BaseAdapterItem<Student>(item) {
    var imageUrl: String? = null
}