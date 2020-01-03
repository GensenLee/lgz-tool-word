package com.gensen.tool.word.utils;

import com.gensen.tool.word.exception.FileTypeException;
import org.apache.poi.xwpf.usermodel.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 11:48
 */
public class FileTypeUtil {

    public static Map<String ,Integer> imageTypes;


    static {
        imageTypes = new HashMap<>();
        imageTypes.put("jpg" ,Document.PICTURE_TYPE_JPEG);
        Field[] typeFields = Document.class.getDeclaredFields();
        for (Field typeField : typeFields) {
            typeField.setAccessible(true);
            String name = typeField.getName();
            name = name.substring(name.lastIndexOf("_") + 1).toLowerCase();
            try {
                int val = (int) typeField.get(Document.class);
                imageTypes.put(name ,val);
            } catch (IllegalAccessException e) {
            }
        }
    }

    /**
     * 是否图片
     *
     * @param filePath
     * @return
     */
    public static boolean isImage(String filePath) {
        if (StringUtil.isEmpty(filePath)){
            return false;
        }
        String ext = filePath.substring(filePath.lastIndexOf(".") + 1);
        if (imageTypes.keySet().contains(ext)){
            return true;
        }
        return false;
    }

    /**
     * 获取图片文件类型
     *
     * @param fileName
     * @return
     */
    public static int getImageType(String fileName) {
        if (!isImage(fileName)){
            throw new FileTypeException("非图片格式文件");
        }
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        return imageTypes.get(ext);
    }

}
