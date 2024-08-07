package com.own.di.example.test.di.exception;

import java.io.Serial;

public class DataFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1554383918868251724L;

    public DataFormatException(String msg) {
        super(msg);
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
