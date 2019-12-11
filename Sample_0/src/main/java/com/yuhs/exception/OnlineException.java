package com.yuhs.exception;

/**
 * 业务异常
 * Created by yuhaisheng on 2019/9/19.
 */
public class OnlineException extends BaseException {
    public OnlineException() {

    }

    public OnlineException(String s) {
        super(s);
    }

    public OnlineException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public OnlineException(Throwable throwable) {
        super(throwable);
    }
}
