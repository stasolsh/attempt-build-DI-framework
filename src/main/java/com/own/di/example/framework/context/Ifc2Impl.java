package com.own.di.example.framework.context;

import lombok.Builder;
import lombok.Data;

/**
 * The Ifc2Impl class represents a mapping between an interface and its implementation.
 */
@Data
@Builder
public class Ifc2Impl {
    private Class ifc;
    private Class impl;
    private String alias;
}
