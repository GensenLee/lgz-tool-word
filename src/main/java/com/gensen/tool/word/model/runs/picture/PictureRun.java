package com.gensen.tool.word.model.runs.picture;

import com.gensen.tool.word.exception.CommonRuntimeException;
import com.gensen.tool.word.exception.ParameterException;
import com.gensen.tool.word.utils.FileTypeUtil;
import com.gensen.tool.word.utils.StringUtil;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 13:46
 */
public class PictureRun extends BasePicture {
    // 图题
    private String title;

    //文件名
    private String fileName;

    //类型 参考 org.apache.poi.xwpf.usermodel.Document
    private int type;

    // 宽度(单位像素)
    private int widthPixel = 0;

    // 高度(单位像素)
    private int heightPixel = 0;

    // 前回车
    private boolean breakBefore = true;
    // 后回车
    private boolean breakAfter = true;

    public int getType() {
        if (this.type == 0 && StringUtil.isNotEmpty(this.fileName)){
            return FileTypeUtil.getImageType(this.fileName);
        }
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidthPixel() {
        return widthPixel;
    }

    public void setWidthPixel(int widthPixel) {
        this.widthPixel = widthPixel;
    }

    public int getHeightPixel() {
        return heightPixel;
    }

    public void setHeightPixel(int heightPixel) {
        this.heightPixel = heightPixel;
    }

    public boolean isBreakBefore() {
        return breakBefore;
    }

    public void setBreakBefore(boolean breakBefore) {
        this.breakBefore = breakBefore;
    }

    @Override
    public boolean isBreakAfter() {
        return breakAfter;
    }

    @Override
    public void setBreakAfter(boolean breakAfter) {
        this.breakAfter = breakAfter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        if (StringUtil.isEmpty(this.fileName)){
            return StringUtil.randomString(10);
        }
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public XWPFRun getRun(XWPFParagraph paragraph) {
        if (this.widthPixel <= 0){
            throw new ParameterException("widthPixel");
        }
        if (this.heightPixel <= 0){
            throw new ParameterException("heightPixel");
        }
        XWPFRun pictureRun = paragraph.createRun();
        if (this.breakBefore){
            pictureRun.addBreak();
        }
        try {
            pictureRun.addPicture(getPictureInputStream() ,getType() ,getFileName() ,Units.pixelToEMU(this.widthPixel) ,Units.pixelToEMU(this.heightPixel));
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        if (this.breakAfter){
            pictureRun.addBreak();
        }
        return pictureRun;
    }
}
