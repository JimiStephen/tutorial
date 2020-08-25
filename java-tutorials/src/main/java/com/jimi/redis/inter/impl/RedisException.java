package com.jimi.redis.inter.impl;

public class RedisException extends RuntimeException {

    private static final long serialVersionUID = -1611101501690221263L;

    public RedisException(String message) {
        super(message);
    }

    public RedisException(Throwable e) {
        super(e);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }
}