package com.own.di.example.test.di.interfaces;

import com.own.di.example.test.di.entity.ValidationResult;

public interface Validator<V>{
    ValidationResult isValid(V object);
}
