package com.ljj.lcompiler.processor

import com.google.auto.service.AutoService
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


@SupportedAnnotationTypes("com.ljj.lannotation.HttpRepostroy")//注解包名
@AutoService(Processor::class)// 允许/支持的注解类型，让注解处理器处理
class HttpRepostroyProcessor : BaseProcessor() {
    override fun init(environment: ProcessingEnvironment?) {
        super.init(environment)
        messager?.printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>> init:success")
    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {

//        messager?.printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>> options:")
//        messager?.printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>> aptPackage:")
//
//
//        val elements = roundEnvironment?.getElementsAnnotatedWith(HttpRepostroy::class.java)
//        elements?.forEach { element ->
//            messager?.printMessage(Diagnostic.Kind.WARNING, ">>>>>>>>>>>>>>>>>>>>>> ${element}")
//            val enclosingElement = element.enclosedElements
//            messager?.printMessage(Diagnostic.Kind.WARNING, ">>>>>>>>>>>>>>>>>>>>>> ${element}")
//            enclosingElement?.forEach {
//                messager?.printMessage(Diagnostic.Kind.WARNING, ">>>>>>>>>>>>>>>>>>>>>>modifiers==> ${it.modifiers}")
//
//            }
//        }
//
//
//        /**
//         * 定义了方法
//         */
//        val main = FunSpec.builder("gg")
//                .addModifiers(KModifier.PUBLIC)
//                .returns(Void.TYPE)
//                .addParameter("args", Array<String>::class.java)
//                .addStatement("System.out.println(\"Hello, Kotlin!\")")
//                .build()
//
//        /**
//         * 定义类
//         */
//
//        val helloWorld = TypeSpec.classBuilder("HttpRepostroy")
//                .addModifiers(KModifier.PUBLIC, KModifier.OPEN)
//                .addFunctions(arrayListOf(main))
//                .build()
//
//        /**
//         * 定义包名
//         */
//        val javaFile = FileSpec.builder("com.ljj.lettercircle.net", "HttpRepostroy")
//                .addType(helloWorld)
//                .build()
//
//        try {
//            javaFile.writeTo(processingEnv.filer)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        System.out.println("=======>end")
        return true
    }


}
