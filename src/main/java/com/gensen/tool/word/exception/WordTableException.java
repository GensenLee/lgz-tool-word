package com.gensen.tool.word.exception;

/**
 * @author Gensen.Lee
 * @date 2019/12/27 11:52
 */
public class WordTableException extends RuntimeException {

    public WordTableException() {
        super();
    }

    public WordTableException(String message) {
        super("table fill error :" + message);
    }
}
