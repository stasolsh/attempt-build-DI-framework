package com.own.di.example.framework.interfaces;

/**
 * The Config interface provides methods for configuration settings in the application context.
 */
public interface Config {
    /**
     * Retrieves the implementation class for the specified interface type.
     *
     * @param type      the class type of the interface
     * @param qualifier the optional qualifiers for the object
     * @param <T>       the type of the object
     * @return the implementation class
     */
    <T> Class<? extends T> getImplClass(Class<T> type, String... qualifier);
}
