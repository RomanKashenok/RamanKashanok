package com.kashanok.classes.homework.common.workitem

import com.kashanok.classes.common.BaseAdapterItem
import com.kashanok.classes.homework.common.HomeworkListItemAdapter

class HomeworkListItem(item: WorkNumber) : BaseAdapterItem <WorkNumber>(item) {

    override fun getItemType(): Int {
        return HomeworkListItemAdapter.HOMEWORK_ITEM
    }
}