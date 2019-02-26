package com.kashanok.classes.common

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemOffsetDecoration(
    private val firstTopOffset: Int,
    private val topOffset: Int,
    private val startOffset: Int,
    private val endOffset: Int,
    private val bottomOffset: Int,
    private val lastBottomOffset: Int
) : RecyclerView.ItemDecoration() {

    constructor(itemOffset: Int) : this(itemOffset, itemOffset, itemOffset, itemOffset, itemOffset, itemOffset)

    constructor(context: Context, @DimenRes firstTopOffsetId: Int, @DimenRes topOffsetId: Int, @DimenRes startOffsetId: Int, @DimenRes endOffsetId: Int, @DimenRes bottomOffsetId: Int, @DimenRes lastBottomOffsetId: Int) : this(
        context.resources.getDimension(firstTopOffsetId).toInt(),
        context.resources.getDimension(topOffsetId).toInt(),
        context.resources.getDimension(startOffsetId).toInt(),
        context.resources.getDimension(endOffsetId).toInt(),
        context.resources.getDimension(bottomOffsetId).toInt(),
        context.resources.getDimension(lastBottomOffsetId).toInt()
    )

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemCount = state.itemCount
        val itemPosition = parent.getChildAdapterPosition(view)
        with(outRect) {
            top = if (parent.getChildAdapterPosition(view) == 0) firstTopOffset else topOffset
            left = startOffset
            right = endOffset
            bottom = if (itemCount > 0 && itemPosition == itemCount - 1) lastBottomOffset else bottomOffset
        }
    }
}