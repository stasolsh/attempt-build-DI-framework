package com.own.di.example.framework.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeanDefinition {
    private Class clazz;
    private Object object;
    private String alias;
    private String locale;
    private String[] patterns;
}
