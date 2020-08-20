package com.ljj.commonlib.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ljj.commonlib.ui.recyclerview.adapter.BaseListAdpater
import com.ljj.commonlib.ui.recyclerview.dragswipe.DragAndSwipeCallback

/***
 * RecyclerView扩展
 ***/

fun RecyclerView.Adapter<RecyclerView.ViewHolder>.bindRecyclerView(rv: RecyclerView,mLayoutManager: RecyclerView.LayoutManager?=null, func: (rv: RecyclerView) -> Unit = {}): RecyclerView.Adapter<RecyclerView.ViewHolder> {
    rv.layoutManager = mLayoutManager ?: LinearLayoutManager(rv.context)
    rv.overScrollMode = View.OVER_SCROLL_NEVER
    (rv.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    func(rv)
    rv.adapter = this
    return this
}

fun <T> BaseListAdpater<T>.bindRecyclerView(rv: RecyclerView, func: RecyclerView.() -> Unit = {}): BaseListAdpater<T> {
    rv.layoutManager = LinearLayoutManager(rv.context)
    rv.overScrollMode = View.OVER_SCROLL_NEVER
    (rv.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    rv.func()
    rv.adapter = this
    return this
}

fun <T> BaseListAdpater<T>.bindRecyclerView(rv: RecyclerView, mLayoutManager: RecyclerView.LayoutManager?, func: (rv: RecyclerView) -> Unit = {}): BaseListAdpater<T> {
    rv.layoutManager = mLayoutManager ?: LinearLayoutManager(rv.context)
    rv.overScrollMode = View.OVER_SCROLL_NEVER
    (rv.itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
    func(rv)
    rv.adapter = this
    return this
}

fun <T> BaseListAdpater<T>.bindItemTouchHelper(rv: RecyclerView): BaseListAdpater<T> {
    ItemTouchHelper(DragAndSwipeCallback(this)).attachToRecyclerView(rv)
    return this
}



