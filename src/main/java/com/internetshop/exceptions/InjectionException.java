package com.internetshop.exceptions;

public class InjectionException extends RuntimeException {
    public InjectionException(String message) {
        super(message);
    }

    public InjectionException(String message, Throwable e) {
        super(message, e);
    }
}
