package com.jeferry.android.compiler;

import com.jeferry.android.annotation.SettingKey;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

public class SettingKeyProcessor extends AbstractProcessor {

    public synchronized void init(ProcessingEnvironment processingEnv) {
        //init操作
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //注意要点
        //1.process会多次调用。
        //2.annotations可以获取刚刚定义的注解种类，
        //3.roundEnv.getElementsAnnotatedWith(SettingKey.class)获取列表
        //4.获取注解的类，生成子类XXX$$Impl.java（路径build/generated/source/apt/debug），生成对方的方法，添加对应的方法块。

        return true;
    }

    @Override
    public Set getSupportedAnnotationTypes() {
        //添加自定义的注解种类
        Set types = new LinkedHashSet<>();
        types.add(SettingKey.class.getCanonicalName());
        return types;
    }
}
