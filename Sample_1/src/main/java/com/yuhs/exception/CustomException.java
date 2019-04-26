package com.yuhs.exception;

import com.alibaba.druid.wall.violation.ErrorCode;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
public class CustomException extends Exception {
    private String errorMessage;
    private ErrorCode errorCode;

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CustomException(String errorMessage, ErrorCode errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
