package com.gensen.tool.word.test.vo;

import com.gensen.tool.word.annotation.TableCell;
import com.gensen.tool.word.model.table.AbstractTableRow;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

/**
 * @author Gensen.Lee
 * @date 2019/12/28 18:28
 */
public class TableTitleRow extends AbstractTableRow {

    @TableCell(bold = true , fontSize = 20 /*,width = "50%"*/)
    private String cell1;

    @TableCell(bold = true,width = "")
    private String cell2;

    @TableCell(bold = true)
    private String cell3;

    @TableCell(bold = true)
    private String cell4;

    @TableCell(bold = true)
    private String cell5;


    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getCell3() {
        return cell3;
    }

    public void setCell3(String cell3) {
        this.cell3 = cell3;
    }

    public String getCell4() {
        return cell4;
    }

    public void setCell4(String cell4) {
        this.cell4 = cell4;
    }

    public String getCell5() {
        return cell5;
    }

    public void setCell5(String cell5) {
        this.cell5 = cell5;
    }
}
