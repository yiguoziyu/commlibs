package com.ljj.commonlib.ui.recyclerview.node

import androidx.annotation.LayoutRes

abstract class BaseItemProvider {

    abstract val itemViewType: Int

    abstract val layoutId: Int
        @LayoutRes
        get

}