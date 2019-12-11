package com.yuhs.exception;

/**
 * Created by yuhaisheng on 2019/9/19.
 */
public class BaseException extends RuntimeException {
    public BaseException() {

    }

    public BaseException(String s) {
        super(s);
    }

    public BaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
