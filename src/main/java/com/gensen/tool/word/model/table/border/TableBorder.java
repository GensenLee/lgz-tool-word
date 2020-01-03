package com.gensen.tool.word.model.table.border;

import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 * @author Gensen.Lee
 * @date 2019/12/31 17:43
 */
public class TableBorder {
    private XWPFTable.XWPFBorderType type = XWPFTable.XWPFBorderType.SINGLE;
    private int size = 1;
    private int space;
    private String color = "000000";

    public XWPFTable.XWPFBorderType getType() {
        return type;
    }

    public void setType(XWPFTable.XWPFBorderType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
