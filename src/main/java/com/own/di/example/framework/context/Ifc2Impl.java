package com.own.di.example.framework.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ifc2Impl {
    private Class ifc;
    private Class impl;
    private String alias;
}
