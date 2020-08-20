package com.ljj.commonlib.ui.recyclerview.adapter

import android.animation.Animator
import android.util.Log
import android.view.View
import androidx.annotation.IntegerRes
import androidx.recyclerview.widget.DiffUtil
import com.ljj.commonlib.ui.recyclerview.BaseViewHolder
import com.ljj.commonlib.ui.recyclerview.animation.*
import com.ljj.commonlib.ui.recyclerview.diff.ItemDiffCallBack
import com.ljj.commonlib.ui.recyclerview.listener.RecyclerViewItemLongClick


abstract class BaseListAdpater<T>(@IntegerRes val brId: Int? = null, val additionMap: Map<Int, Any>? = null, layoutId: Int? = null) : BaseAdpater<BaseViewHolder>(layoutId) {
    companion object {
        private const val TAG = "BaseListAdpater"
    }

    private var mRecyclerViewItemLongClick: RecyclerViewItemLongClick? = null

    val dataList = mutableListOf<T>()

    //拖拽Flag
    var mDragEnable = false

    //滑动Flag
    var mSwipeEnable = false

    //动画Flag
    var mAnimationEnable = false

    //动画是否是第一次
    var isAnimationFirstOnly = true

    private var mLastPosition = -1

    private var mRecyclerviewItemClick: RecyclerviewItemClick<T>? = null
    private var mRecyclerviewEmptyItemClick: RecyclerviewEmptyItemClick? = null

    /**
     * 设置自定义动画
     */
    var adapterAnimation: BaseAnimation? = null
        set(value) {
            mAnimationEnable = true
            field = value
        }

    override fun getItemCount(): Int = dataList.size

    fun addData(newDataList: MutableList<T>) {
        dataList.addAll(newDataList)
        notifyItemRangeInserted(dataList.size, newDataList.size)
    }

    /**
     * 单一列表刷新
     */
    fun setData(newDataList: MutableList<T>) {
        val diffResult = DiffUtil.calculateDiff(ItemDiffCallBack(dataList, newDataList), true)
        dataList.clear()
        dataList.addAll(newDataList)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * 复杂嵌套列表
     */
    fun setMultipleData(newDataList: MutableList<T>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }



    fun removeData(data: T) {
        val index = findIndex(data)
        if (index == -1) return
        dataList.remove(data)
        notifyItemRemoved(index)
        updateNeedChangeView(index)
    }

    fun removeAll() {
        val diffResult = DiffUtil.calculateDiff(ItemDiffCallBack(dataList, mutableListOf()), true)
        dataList.clear()
        diffResult.dispatchUpdatesTo(this)
    }

    fun removeAt(index: Int) {
        if (checkIndexOutofBounds(index)) return
        dataList.removeAt(index)
        notifyItemRemoved(index)
        updateNeedChangeView(index)
    }

    /**
     * 刷新需要刷新的Item,防止postion位置错乱
     */
    private fun updateNeedChangeView(index: Int) {
        if (index != dataList.size) {
            notifyItemRangeChanged(index, dataList.size - index)
        }
    }

    fun notifyData(data: T, index: Int) {
        if (checkIndexOutofBounds(index)) return
        dataList[index] = data
        notifyItemRangeChanged(index, 1)
    }

    fun addData(data: T) {
        dataList.add(data)
        val index = findIndex(data)
        if (index == -1) return
        notifyItemInserted(index)
        updateNeedChangeView(index)
    }

    fun addData(index: Int, data: T) {
        if (checkIndexOutofBounds(index)) return
        dataList.add(index, data)
        notifyItemInserted(index)
        updateNeedChangeView(index)
    }


    fun findIndex(data: T): Int = dataList.indexOf(data)

    fun checkIndexOutofBounds(index: Int): Boolean {
        return index >= dataList.size || index < 0
    }


    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (mAnimationEnable || holder.layoutPosition > mLastPosition) {
            val animation = adapterAnimation ?: SlideInBottomAnimation()
            animation.animators(holder.itemView).forEach {
                startAnim(it, holder.layoutPosition)
            }
            mLastPosition = holder.layoutPosition
        }
    }

    /**
     * 开始执行动画方法
     * 可以重写此方法，实行更多行为
     *
     * @param anim
     * @param index
     */
    protected open fun startAnim(anim: Animator, index: Int) {
        anim.start()
    }

    /**
     * 内置默认动画类型
     */
    enum class AnimationType {
        AlphaIn, ScaleIn, SlideInBottom, SlideInLeft, SlideInRight
    }

    /**
     * 使用内置默认动画设置
     * @param animationType AnimationType
     */
    fun setAnimationWithDefault(animationType: AnimationType) {
        adapterAnimation = when (animationType) {
            AnimationType.AlphaIn -> AlphaInAnimation()
            AnimationType.ScaleIn -> ScaleInAnimation()
            AnimationType.SlideInBottom -> SlideInBottomAnimation()
            AnimationType.SlideInLeft -> SlideInLeftAnimation()
            AnimationType.SlideInRight -> SlideInRightAnimation()
        }
    }

    /**
     * 设置监听
     */
    fun setItemClick(mRecyclerviewItemClick: RecyclerviewItemClick<T>): BaseListAdpater<T> {
        this.mRecyclerviewItemClick = mRecyclerviewItemClick
        return this
    }

    /**
     * 绑定Click监听
     */
    fun bindItemClick(view: View, position: Int): BaseListAdpater<T> {
        view.isClickable = true
        view.setOnClickListener {
            Log.e(TAG, "bindItemClick===>${position}---${dataList[position].toString()}")
            mRecyclerviewItemClick?.setItemClick(view, position, dataList[position])
        }
        return this
    }

    /**
     * 绑定空数据监听,用与头部，复杂布局
     */
    fun bindEmptyClick(view: View, position: Int): BaseListAdpater<T> {
        view.setOnClickListener {
            if (dataList.size > 0) {
                mRecyclerviewEmptyItemClick?.setItemClick(view, position)
            }
        }
        return this
    }

    fun setRecyclerviewEmptyItemClick(mRecyclerviewEmptyItemClick: RecyclerviewEmptyItemClick): BaseListAdpater<T> {
        this.mRecyclerviewEmptyItemClick = mRecyclerviewEmptyItemClick
        return this
    }

    /**
     * 设置长按监听
     */
    fun setRecyclerViewItemLongClick(mRecyclerViewItemLongClick: RecyclerViewItemLongClick): BaseListAdpater<T> {
        this.mRecyclerViewItemLongClick = mRecyclerViewItemLongClick
        return this
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        bindItemClick(holder.itemView, position)
        holder.itemView.setOnLongClickListener {
            mRecyclerViewItemLongClick?.onItemLongClick(holder.bindingAdapterPosition)
            return@setOnLongClickListener mRecyclerViewItemLongClick != null
        }
        if (brId != null) {
            holder.binding.setVariable(brId, dataList[position])
            additionMap?.let { map ->
                for ((key, value) in map) {
                    holder.binding.setVariable(key, value)
                }
            }
            onBindViewData(holder, holder.bindingAdapterPosition, mutableListOf())
            holder.binding.executePendingBindings()
        } else {
            onBindViewData(holder, holder.bindingAdapterPosition, mutableListOf())
        }

    }

    open fun onBindViewData(holder: BaseViewHolder,
                            position: Int,
                            payloads: MutableList<Any>) {
    }
}

interface RecyclerviewItemClick<T> {
    fun setItemClick(v: View, pos: Int, data: T)
}

interface RecyclerviewEmptyItemClick {
    fun setItemClick(v: View, pos: Int)
}
