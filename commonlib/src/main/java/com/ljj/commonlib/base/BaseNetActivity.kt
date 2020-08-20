package com.ljj.commonlib.base

import android.os.Bundle
import com.ljj.commonlib.kit.net.NetStatusLifecycle

abstract class BaseNetActivity : BaseActivity() {

    override fun initView(savedInstanceState: Bundle?) {
        lifecycle.addObserver(NetStatusLifecycle)
    }

}