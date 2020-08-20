package com.ljj.commonlib.ui.recyclerview.node

abstract class BaseNode {
    abstract val childNode: MutableList<BaseNode>?
}