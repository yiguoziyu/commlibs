package com.ljj.commonlib.ui.recyclerview.diff

import androidx.recyclerview.widget.DiffUtil


class ItemDiffCallBack<T>(var mOldList: List<T>?, var mNewList: List<T>?) : DiffUtil.Callback() {

    companion object {
        private const val TAG = "ItemDiffCallBack"
    }

    //旧集合数目
    override fun getOldListSize(): Int = mOldList?.size ?: 0

    //新集合数目
    override fun getNewListSize(): Int = mNewList?.size ?: 0

    //判断是否是同一位置的是否是同一对象
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldList?.get(oldItemPosition)== mNewList?.get(newItemPosition)

    //内容是否相同
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        mOldList?.get(oldItemPosition) == mNewList?.get(newItemPosition)

}