package com.gensen.tool.word.model.table;

import com.gensen.tool.word.model.table.border.TableBorder;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import java.util.List;

/**
 * 页内表格
 *
 * @author Gensen.Lee
 * @date 2019/12/16 17:22
 */
public class AbstractTable {
    //宽度
    //String matching one of "auto", [0-9]+, or [0-9]+(\.[0-9]+)%.
    private String witdth;
    //初始列数
    private int colSize = 0;
    //水平对齐方式
    private STJc.Enum hAlign = STJc.CENTER;

    //行数据
    private List<? extends AbstractTableRow> rowData;

    private TableRowAlign tableAlignment = TableRowAlign.CENTER;

    private TableBorder topBorder = new TableBorder();
    private TableBorder rightBorder = new TableBorder();
    private TableBorder bottomBorder = new TableBorder();
    private TableBorder leftBorder = new TableBorder();
    private TableBorder insideHBorder = new TableBorder();
    private TableBorder insideVBorder = new TableBorder();


    public TableBorder getInsideHBorder() {
        return insideHBorder;
    }

    public void setInsideHBorder(TableBorder insideHBorder) {
        this.insideHBorder = insideHBorder;
    }

    public TableBorder getInsideVBorder() {
        return insideVBorder;
    }

    public void setInsideVBorder(TableBorder insideVBorder) {
        this.insideVBorder = insideVBorder;
    }

    public TableBorder getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(TableBorder topBorder) {
        this.topBorder = topBorder;
    }

    public TableBorder getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(TableBorder rightBorder) {
        this.rightBorder = rightBorder;
    }

    public TableBorder getBottomBorder() {
        return bottomBorder;
    }

    public void setBottomBorder(TableBorder bottomBorder) {
        this.bottomBorder = bottomBorder;
    }

    public TableBorder getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(TableBorder leftBorder) {
        this.leftBorder = leftBorder;
    }

    protected AbstractTable() {
    }

    public TableRowAlign getTableAlignment() {
        return tableAlignment;
    }

    public void setTableAlignment(TableRowAlign tableAlignment) {
        this.tableAlignment = tableAlignment;
    }

    public List<? extends AbstractTableRow> getRowData() {
        return rowData;
    }

    public void setRowData(List<? extends AbstractTableRow> rowData) {
        this.rowData = rowData;
    }

    public String getWitdth() {
        return witdth;
    }

    public void setWitdth(String witdth) {
        this.witdth = witdth;
    }

    public int getRowSize() {
        return rowData.size();
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public STJc.Enum gethAlign() {
        return hAlign;
    }

    public void sethAlign(STJc.Enum hAlign) {
        this.hAlign = hAlign;
    }
}
