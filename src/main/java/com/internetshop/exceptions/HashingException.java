package com.internetshop.exceptions;

public class HashingException extends RuntimeException {
    public HashingException(String message, Throwable e) {
        super(message, e);
    }
}
