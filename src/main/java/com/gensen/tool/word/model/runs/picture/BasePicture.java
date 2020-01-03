package com.gensen.tool.word.model.runs.picture;

import com.gensen.tool.word.exception.CommonRuntimeException;
import com.gensen.tool.word.exception.FileNotFoundException;
import com.gensen.tool.word.exception.FileTypeException;
import com.gensen.tool.word.model.runs.AbstractRun;
import com.gensen.tool.word.utils.FileTypeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 11:43
 */
public abstract class BasePicture extends AbstractRun {
    // 图片数据流
    private InputStream pictureInputStream;

    public InputStream getPictureInputStream() {
        if (pictureInputStream == null){
            throw new CommonRuntimeException("未设置 pictureInputStream");
        }
        return pictureInputStream;
    }

    public void setPicture(InputStream pictureInputStream) {
        this.pictureInputStream = pictureInputStream;
    }

    public void setPicture(String pitureFilePath) {
        if (!FileTypeUtil.isImage(pitureFilePath)){
            throw new FileTypeException(pitureFilePath);
        }
        File file = new File(pitureFilePath);
        if (!file.exists()){
            throw new FileNotFoundException(pitureFilePath);
        }
        try {
            this.pictureInputStream = new FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            throw new FileNotFoundException(pitureFilePath ,e);
        }
    }
}
