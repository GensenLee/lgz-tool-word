package com.gensen.tool.word.annotation;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.lang.annotation.*;

/**
 * @author Gensen.Lee
 * @date 2019/12/27 14:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Inherited
@Documented
public @interface TableCell {

    // 背景颜色RGB
    String bgColor() default "";

    // 字体颜色RGB
    String textColor() default "";

    // 单元格宽度百分比 如 23%
    // auto|[0-9]+|[0-9]+(\.[0-9]+)?%
    String width() default "";

    //加粗
    boolean bold() default false;
    //斜体
    boolean italic() default false;
    //字体大小
    int fontSize() default 12;
    //字体
    String fontFamily() default "";

    //垂直对齐方式
    XWPFTableCell.XWPFVertAlign verticalAlign() default XWPFTableCell.XWPFVertAlign.CENTER;

    // 文本对齐方式
    ParagraphAlignment textAlignment() default ParagraphAlignment.CENTER;

}
