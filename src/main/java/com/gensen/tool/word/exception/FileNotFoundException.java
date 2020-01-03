package com.gensen.tool.word.exception;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 11:59
 */
public class FileNotFoundException extends FileException{

    public FileNotFoundException() {
        super("文件不存在");
    }

    public FileNotFoundException(String message) {
        super("文件不存在 [" + message + "]");
    }

    public FileNotFoundException(String message ,Throwable cause) {
        super(message ,cause);
    }
}
