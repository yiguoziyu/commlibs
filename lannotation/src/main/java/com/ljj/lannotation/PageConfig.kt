package com.ljj.lannotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PageConfig(val layoutId: Int = 0, val title: String = "")