package com.internetshop.exceptions;

public class DbConnectionException extends RuntimeException {
    public DbConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
