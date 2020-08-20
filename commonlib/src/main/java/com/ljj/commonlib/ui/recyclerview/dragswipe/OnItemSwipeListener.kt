package com.ljj.commonlib.ui.recyclerview.dragswipe

import androidx.recyclerview.widget.RecyclerView

interface OnItemSwipeListener {
    fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder, pos: Int)
    fun onItemSwiped(viewHolder: RecyclerView.ViewHolder, pos: Int)
    fun onItemSwipedEnd(viewHolder: RecyclerView.ViewHolder, pos: Int)
}