package com.ljj.commonlib.ui.recyclerview.dragswipe

import androidx.recyclerview.widget.RecyclerView

/**
 * 拖拽回调
 */
interface OnItemDragListener {
    fun onItemDragStart(viewHolder: RecyclerView.ViewHolder,pos:Int)
    fun onItemDragMoving(source: RecyclerView.ViewHolder,from:Int,target:RecyclerView.ViewHolder,to:Int)
    fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder,pos:Int)
}