package com.own.di.example.test.di;

import com.own.di.example.framework.context.ApplicationContext;

public class
ApplicationRunner {
    public static ApplicationContext run(String packageToscan) {
        return new ApplicationContext(packageToscan);
    }
}
