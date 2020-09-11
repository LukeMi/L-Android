package com.jeferry.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface SettingKey {

//    String values();

    int defaultInt() default 0;

    long defaultLong() default 0L;

    float defaultFloat() default 0F;

    boolean defaultBoolean() default false;

    String defaultString() default "";
}

