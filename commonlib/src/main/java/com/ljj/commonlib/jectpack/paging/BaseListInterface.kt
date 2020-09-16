package com.ljj.commonlib.jectpack.paging

import androidx.recyclerview.widget.RecyclerView
import com.ljj.commonlib.ui.recyclerview.adapter.BaseListAdpater

interface BaseListInterface {

    /**
     * 新增数据
     */
    fun <T> addData(adpater: BaseListAdpater<T>, index: Int, data: T)

    /**
     * 新增数据
     */
    fun <T> addData(adpater: BaseListAdpater<T>, data: T)
    /**
     * 删除数据
     */
    fun <T> removeData(adpater: BaseListAdpater<T>, data: T)


    /**
     * 根据集合设置View
     */
    fun <T> checkDataEmpty(dataList: MutableList<T>)

    /**
     * 刷新所有数据
     */
    fun <T> setDataNotify(adpater: BaseListAdpater<T>, dataList: MutableList<T>)

    /**
     * 设置数据
     */
    fun <T> setData(adpater: BaseListAdpater<T>, dataList: MutableList<T>, rv: RecyclerView? = null)

    /**
     * 设置嵌套的数据
     */
    fun <T> setMultipleData(adpater: BaseListAdpater<T>, dataList: MutableList<T>)


    fun onRefresh()

    fun onRefreshWithAnimator()


     fun onLoadData()
}