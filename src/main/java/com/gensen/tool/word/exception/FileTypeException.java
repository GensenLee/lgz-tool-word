package com.gensen.tool.word.exception;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 11:55
 */
public class FileTypeException extends FileException {

    public FileTypeException() {
        super("文件格式异常");
    }

    public FileTypeException(String message) {
        super("文件格式异常 [" + message + "]");
    }
}
