package com.gensen.tool.word.exception;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 11:58
 */
public class FileException extends RuntimeException {

    public FileException() {
        super("文件异常");
    }

    public FileException(String message) {
        super("文件异常 -> " + message);
    }

    public FileException(String message ,Throwable cause) {
        super("文件异常 -> " + message ,cause);
    }
}
