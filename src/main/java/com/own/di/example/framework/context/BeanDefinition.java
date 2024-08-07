package com.own.di.example.framework.context;

import lombok.Builder;
import lombok.Data;

/**
 * The BeanDefinition class represents the definition of a bean, including its class, object, alias, locale, and patterns.
 */
@Data
@Builder
public class BeanDefinition {
    private Class clazz;
    private Object object;
    private String alias;
    private String locale;
    private String[] patterns;
}
