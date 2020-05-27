package com.internetshop.exceptions;

import com.internetshop.util.HashUtil;

public class HashingException extends RuntimeException {
    public HashingException(String message, Throwable e) {
        super(message, e);
    }
}
