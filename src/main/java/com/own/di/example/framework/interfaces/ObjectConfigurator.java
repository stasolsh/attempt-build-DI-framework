package com.own.di.example.framework.interfaces;

import com.own.di.example.framework.context.ApplicationContext;

/**
 * The ObjectConfigurator interface provides methods for configuring objects in the application context.
 */
public interface ObjectConfigurator {
    /**
     * Configures the specified object in the context.
     *
     * @param t       the object to configure
     * @param context the application context
     */
    void configure(Object t, ApplicationContext context);
}
