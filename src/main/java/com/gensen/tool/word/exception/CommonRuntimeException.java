package com.gensen.tool.word.exception;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 14:25
 */
public class CommonRuntimeException extends RuntimeException {

    public CommonRuntimeException() {
        super();
    }

    public CommonRuntimeException(String message) {
        super(message);
    }

    public CommonRuntimeException(String message ,Throwable cause) {
        super(message ,cause);
    }

    public CommonRuntimeException(Throwable cause) {
        super(cause);
    }

    public CommonRuntimeException(String message ,Throwable cause ,boolean enableSuppression ,boolean writableStackTrace) {
        super(message ,cause ,enableSuppression ,writableStackTrace);
    }
}
