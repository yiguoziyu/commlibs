package com.ljj.lcompiler.util

import javax.annotation.processing.Messager
import javax.tools.Diagnostic


class Logger {
    private var msg: Messager? = null

    fun Logger(messager: Messager?) {
        msg = messager
    }

    /**
     * Print info log.
     */
    fun info(info: CharSequence) {
        msg?.printMessage(Diagnostic.Kind.NOTE, info)
    }

    fun error(error: CharSequence) {
        msg?.printMessage(Diagnostic.Kind.ERROR, "An exception is encountered, [$error]")
    }

    fun error(error: Throwable?) {
        msg?.printMessage(Diagnostic.Kind.ERROR, "An exception is encountered, [" + error?.message + "]" + "\n" )
    }

    fun warning(warning: CharSequence) {
        msg?.printMessage(Diagnostic.Kind.WARNING, warning)
    }

    private fun formatStackTrace(stackTrace: Array<StackTraceElement>): String {
        val sb = StringBuilder()
        for (element in stackTrace) {
            sb.append("    at ").append(element.toString())
            sb.append("\n")
        }
        return sb.toString()
    }
}