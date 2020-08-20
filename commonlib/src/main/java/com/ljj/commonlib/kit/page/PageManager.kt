package com.ljj.commonlib.kit.page

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * 跳转页面
 */
fun Context.openPage(cls: Class<out Any?>, func: Intent.() -> Unit = {}) {
    val intent = Intent(this, cls)
    intent.func()
    this.startActivity(intent)
}


fun FragmentActivity.openPage(cls: Class<out Any?>, func: Intent.() -> Unit = {}) {
    val intent = Intent(this, cls)
    intent.func()
    this.startActivity(intent)
}
fun FragmentActivity.openPageAndFinish(cls: Class<out Any?>, func: Intent.() -> Unit = {}) {
    val intent = Intent(this, cls)
    intent.func()
    this.startActivity(intent)
    this.finish()
}
fun FragmentActivity.openPageAndResult(cls: Class<out Any?>,requestCode:Int,func: Intent.() -> Unit={}){
    val intent = Intent(this, cls)
    intent.func()
    this.startActivityForResult(intent,requestCode)
}
fun Fragment.openPage(cls: Class<out Any?>, func: Intent.() -> Unit = {}) {
    requireActivity().openPage(cls, func)
}





