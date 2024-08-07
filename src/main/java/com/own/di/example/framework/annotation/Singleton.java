package com.own.di.example.framework.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Singleton {
    String qualifier() default "";
}
