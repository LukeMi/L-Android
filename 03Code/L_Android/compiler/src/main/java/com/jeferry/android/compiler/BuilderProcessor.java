package com.jeferry.android.compiler;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes(Const.BUILDER_PROCESSOR)
@AutoService(Process.class)
public class BuilderProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        for (TypeElement annotation : set) {
            Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(annotation);

        }
        return false;
    }
}
