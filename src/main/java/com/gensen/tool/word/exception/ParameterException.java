package com.gensen.tool.word.exception;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 14:11
 */
public class ParameterException extends RuntimeException {

    public ParameterException() {
        super("参数异常");
    }

    public ParameterException(String message) {
        super("参数异常 [" + message + "]");
    }
}
