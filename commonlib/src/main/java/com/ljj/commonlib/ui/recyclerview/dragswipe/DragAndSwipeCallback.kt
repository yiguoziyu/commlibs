package com.ljj.commonlib.ui.recyclerview.dragswipe

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * 拖拽回调
 */
class DragAndSwipeCallback<VH : RecyclerView.ViewHolder>(private val mAdapter: RecyclerView.Adapter<VH>) :
    ItemTouchHelper.Callback() {
    private val mDragMoveFlags =
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    private val mSwipeMoveFlags = ItemTouchHelper.END or ItemTouchHelper.START

    companion object {
        private const val TAG = "DragAndSwipeCallback"
    }

    //设置拖拽滑动方向
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        Log.e(TAG, "getMovementFlags")
        return makeMovementFlags(mDragMoveFlags, mSwipeMoveFlags)
    }

    //拖拽时回调
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Log.e(TAG, "onMoved")
        return viewHolder.itemViewType == target.itemViewType
    }

    override fun onMoved(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        fromPos: Int,
        target: RecyclerView.ViewHolder,
        toPos: Int,
        x: Int,
        y: Int
    ) {
        Log.e(TAG, "onMoved2====>${mAdapter is OnItemDragListener}")
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
        if (mAdapter is OnItemDragListener) {
            mAdapter.onItemDragMoving(
                viewHolder,
                viewHolder.bindingAdapterPosition,
                target,
                target.bindingAdapterPosition
            )
        }
    }


    //移动时回调
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.e(TAG, "onSwiped====>${mAdapter is OnItemSwipeListener}")
        if (mAdapter is OnItemSwipeListener) {
            mAdapter.onItemSwiped(viewHolder, viewHolder.bindingAdapterPosition)
        }
    }


    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        Log.e(TAG, "clearView")
        super.clearView(recyclerView, viewHolder)
        if (mAdapter is OnItemDragListener) {
            mAdapter.onItemDragEnd(viewHolder, viewHolder.bindingAdapterPosition)
        }
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        Log.e(TAG, "onSelectedChanged")
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                if (mAdapter is OnItemDragListener) {
                    viewHolder?.let {
                        mAdapter.onItemDragStart(it, it.bindingAdapterPosition)
                    }
                }
            }
            ItemTouchHelper.ACTION_STATE_SWIPE -> {
                if (mAdapter is OnItemSwipeListener) {
                    viewHolder?.let {
                        mAdapter.onItemSwipeStart(it, it.bindingAdapterPosition)
                    }
                }

            }
        }
    }

}