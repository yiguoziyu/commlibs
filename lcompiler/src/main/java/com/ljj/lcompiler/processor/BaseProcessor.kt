package com.ljj.lcompiler.processor

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.Messager
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

abstract class BaseProcessor : AbstractProcessor() {
    // Message用来打印 日志相关信息
    var messager: Messager? = null
    // 文件生成器， 类 资源 等，就是最终要生成的文件 是需要Filer来完成的
    var filer: Filer? = null
    // 操作Element的工具类（类，函数，属性，其实都是Element）
    var elementUtils: Elements? = null
    // type(类信息)的工具类，包含用于操作TypeMirror的工具方法
    var typeTool: Types? = null

    override fun init(environment: ProcessingEnvironment?) {
        super.init(environment)
        environment?.let {
            messager = it.messager
            elementUtils = it.elementUtils
            typeTool = it.typeUtils
            filer = it.filer
        }
        printMessage(">>>>>>>>>>>>>>>>>>>>>> ${this.javaClass.canonicalName} init")
    }

    fun printMessage(content: String,kind: Diagnostic.Kind = Diagnostic.Kind.WARNING) {
        messager?.printMessage(kind, content)
    }
}