package com.ljj.commonlib.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ljj.commonlib.ui.recyclerview.BaseViewHolder

/**
 * 需要根据viewType来设置多布局则使用getLayoutId
 * 单一布局使用layoutId来构建
 */
abstract class BaseAdpater<T>(val layoutId: Int? = null) : BaseEmplyAdapter<BaseViewHolder>() {
    open fun getLayoutId(viewType: Int): Int {
        return -1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId
                ?: getLayoutId(viewType), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}