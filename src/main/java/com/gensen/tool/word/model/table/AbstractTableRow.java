package com.gensen.tool.word.model.table;

import com.gensen.tool.word.annotation.TableCell;
import com.gensen.tool.word.utils.StringUtil;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

import java.lang.reflect.Field;
import java.util.List;

/** 表格行
 * @author Gensen.Lee
 * @date 2019/12/27 14:07
 */
public abstract class AbstractTableRow {
    /**
     * This attribute controls whether to repeat a table's header row at the top
     * of a table split across pages. NOTE - for a row to be repeated, all preceding
     * rows in the table must also be repeated.
     *
     * @param repeat - if TRUE, repeat header row at the top of each page of table;
     *               if FALSE, don't repeat header row.
     */
    private boolean repeatHeader = false;

    /**
     * This element specifies the height of the current table row within the
     * current table. This height shall be used to determine the resulting
     * height of the table row, which may be absolute or relative (depending on
     * its attribute values). If omitted, then the table row shall automatically
     * resize its height to the height required by its contents (the equivalent
     * of an hRule value of auto).
     *
     * @param height
     */
    private int height;


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isRepeatHeader() {
        return repeatHeader;
    }

    public void setRepeatHeader(boolean repeatHeader) {
        this.repeatHeader = repeatHeader;
    }

    public void fillToRow(XWPFTableRow row){
        Field[] fields = this.getClass().getDeclaredFields();
        List<XWPFTableCell> cells = row.getTableCells();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            TableCell annotation = field.getAnnotation(TableCell.class);
            if (annotation != null){
                XWPFTableCell xwpfTableCell = cells.get(i);
                xwpfTableCell.setColor(annotation.bgColor());
                xwpfTableCell.setVerticalAlignment(annotation.verticalAlign());
                if (StringUtil.isNotEmpty(annotation.width())){
                    xwpfTableCell.setWidth(annotation.width());
                }
                XWPFParagraph paragraphArray = xwpfTableCell.getParagraphArray(0);
                paragraphArray.setAlignment(annotation.textAlignment());
                XWPFRun xwpfRun = paragraphArray.createRun();
                xwpfRun.setBold(annotation.bold());
                xwpfRun.setFontSize(annotation.fontSize());
                xwpfRun.setFontFamily(annotation.fontFamily());
                xwpfRun.setColor(annotation.textColor());
                xwpfRun.setItalic(annotation.italic());
                field.setAccessible(true);
                try {
                    xwpfRun.setText(field.get(this).toString());
                } catch (IllegalAccessException e) {
                }finally {
                    field.setAccessible(false);
                }
            }
        }

    }

}
