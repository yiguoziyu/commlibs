package com.ljj.lcompiler.processor

import com.google.auto.service.AutoService
import com.ljj.lannotation.Persistence
import com.squareup.kotlinpoet.*
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.TypeElement


@SupportedAnnotationTypes("com.ljj.lannotation.Persistence")//注解包名
@AutoService(Processor::class)// 允许/支持的注解类型，让注解处理器处理
class PersistenceProcessor : BaseProcessor() {
    private val PACKAG_PRE_ENAME="com.ljj.lettercircel"
    private val PACKAGENAME = "$PACKAG_PRE_ENAME.viewmodels.persistences"
    private val mSuffix = "PersistenceViewModel"
    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        //获取所有带有Persistence注解的类
        val rootEnironments = roundEnvironment?.getElementsAnnotatedWith(Persistence::class.java)
        rootEnironments?.forEach { element ->
            printMessage("=====>${element.simpleName}")
            val mClassK = ClassName.bestGuess("$PACKAG_PRE_ENAME.model.${element.simpleName}")


            val methodList = mutableListOf<FunSpec>()
            val propertySpec = PropertySpec.builder("cacheKey", String::class, KModifier.FINAL, KModifier.PRIVATE)
                    .initializer("\"persistence_${mClassK.simpleName}\"")
                    .build()
            //增
            val insetFunSpec = FunSpec.builder("insert")
                    .addModifiers(KModifier.PUBLIC)
                    .addParameter("bean", mClassK)
                    .addCode(" ACache.get(BaseApplication.application).put(cacheKey, bean)")
                    .build()

            //删
            val removeFunSpec = FunSpec.builder("remove")
                    .addModifiers(KModifier.PUBLIC)
                    .addCode("ACache.get(BaseApplication.application).remove(cacheKey);")
                    .build()

            //改
            val updateFunSpec = FunSpec.builder("update")
                    .addModifiers(KModifier.PUBLIC)
                    .addParameter("bean", mClassK)
                    .addCode("ACache.get(BaseApplication.application).put(cacheKey, bean)")
                    .build()
            //查
            val queryFunSpec = FunSpec.builder("query")
                    .addModifiers(KModifier.PUBLIC)
                    .addCode(" val instance = ACache.get(BaseApplication.application).getAsObject(cacheKey)\n" +
                            "        return if (instance == null) {\n" +
                            "            ${mClassK.simpleName}()\n" +
                            "        } else {\n" +
                            "            instance as ${mClassK.simpleName}\n" +
                            "        }")
                    .returns(mClassK)
                    .build()

            methodList.add(insetFunSpec)
            methodList.add(removeFunSpec)
            methodList.add(updateFunSpec)
            methodList.add(queryFunSpec)
            val mSuperClass = ClassName.bestGuess("com.ljj.commonlib.jectpack.viewmodel.BasePersisenceViewModel")
            //创建类
            val mTargetClass = TypeSpec.classBuilder("${mClassK.simpleName}$mSuffix")
                    .addProperty(propertySpec)
                    .superclass(mSuperClass)
                    .addModifiers(KModifier.PUBLIC)
                    .addFunctions(methodList)
                    .build()

            //创建包
            val mFile = FileSpec.builder(PACKAGENAME, "${mClassK.simpleName}$mSuffix")
                    .addImport("com.ljj.commonlib.kit.cache", "ACache")
                    .addImport("com.ljj.commonlib.base", "BaseApplication")
                    .addType(mTargetClass)
                    .build()

            try {
                mFile.writeTo(processingEnv.filer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return true
    }


}
