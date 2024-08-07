package com.own.di.example.framework.interfaces;

import com.own.di.example.framework.context.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
