package com.own.di.example.framework.interfaces;

public interface Config {
    <T> Class<? extends T>  getImplClass(Class<T> type, String... qualifier);
}
