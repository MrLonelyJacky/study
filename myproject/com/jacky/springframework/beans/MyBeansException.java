package com.jacky.springframework.beans;

/**
 *
 */
public class MyBeansException extends RuntimeException {

    public MyBeansException(String msg) {
        super(msg);
    }

    public MyBeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
