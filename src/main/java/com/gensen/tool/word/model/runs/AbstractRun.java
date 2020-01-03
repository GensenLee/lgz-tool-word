package com.gensen.tool.word.model.runs;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author Gensen.Lee
 * @date 2019/12/27 14:59
 */
public abstract class AbstractRun {

    // 加粗
    private boolean bold = false;

    //字体大小
    private int fontSize = 12;

    // 字体RGB颜色
    private String color = "000000";

    //字体
    private String fontFamily = "宋体";

    // 斜体
    private boolean italic = false;

    // 下划线
    private UnderlinePatterns underline = UnderlinePatterns.NONE;

    // 下划线颜色
    private String underlineColor = "000000";

    // 内容后换行
    private boolean breakAfter = false;

    private String verticalAlignment = VerticalAlignment.BASELINE;

    public static class VerticalAlignment{
        // 基线
        public static final String BASELINE = "baseline";
        // 上标
        public static final String SUPERSCRIPT = "superscript";
        // 下标
        public static final String SUBSCRIPT = "subscript";

    }

    public boolean isBreakAfter() {
        return breakAfter;
    }

    public void setBreakAfter(boolean breakAfter) {
        this.breakAfter = breakAfter;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public UnderlinePatterns getUnderline() {
        return underline;
    }

    public void setUnderline(UnderlinePatterns underline) {
        this.underline = underline;
    }

    public String getUnderlineColor() {
        return underlineColor;
    }

    public void setUnderlineColor(String underlineColor) {
        this.underlineColor = underlineColor;
    }

    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public abstract XWPFRun getRun(XWPFParagraph paragraph);

}
