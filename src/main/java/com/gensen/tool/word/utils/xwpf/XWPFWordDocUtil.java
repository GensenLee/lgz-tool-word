package com.gensen.tool.word.utils.xwpf;

import com.gensen.tool.word.exception.ParameterException;
import com.gensen.tool.word.model.table.AbstractTable;
import com.gensen.tool.word.model.table.AbstractTableRow;
import com.gensen.tool.word.model.table.border.TableBorder;
import com.gensen.tool.word.utils.CollectionsUtil;
import com.gensen.tool.word.utils.StringUtil;
import com.gensen.tool.word.model.runs.AbstractRun;
import org.apache.poi.xwpf.usermodel.*;

import java.util.List;

/**
 * @author Gensen.Lee
 * @date 2019/12/27 14:43
 */
public class XWPFWordDocUtil {

    public static XWPFDocument creteDocument(){
        return new XWPFDocument();
    }


    public static XWPFParagraph createParagraph(XWPFDocument document ,List<AbstractRun> data){
        return createParagraph(document ,data ,null ,0 ,null);
    }

    public static XWPFParagraph createParagraph(XWPFDocument document ,List<AbstractRun> data ,String fontColor ,int fontSize ,String fontFamily){
        return createParagraph(document ,data ,fontColor ,fontSize ,fontFamily ,ParagraphAlignment.LEFT ,TextAlignment.CENTER ,0);
    }

    public static XWPFParagraph createParagraph(XWPFDocument document ,List<AbstractRun> data ,int firstLineIndent){
        return createParagraph(document ,data ,null ,0,firstLineIndent);
    }

    public static XWPFParagraph createParagraph(XWPFDocument document ,List<AbstractRun> data ,ParagraphAlignment paragraphAlignment ,TextAlignment textAlignment){
        return createParagraph(document ,data ,null ,0,null ,paragraphAlignment ,textAlignment ,0);
    }

    public static XWPFParagraph createParagraph(XWPFDocument document ,List<AbstractRun> data ,String fontColor ,int fontSize ,int firstLineIndent){
        return createParagraph(document ,data ,fontColor ,fontSize ,null ,ParagraphAlignment.LEFT ,TextAlignment.CENTER ,firstLineIndent);
    }

    /**
     * @param document 文档对象
     * @param data 段落内容
     * @param color
     * @param fontSize
     * @param fontFamily
     * @param paragraphAlignment
     * @param textAlignment
     * @param firstLineIndent
     */
    public static XWPFParagraph createParagraph(XWPFDocument document ,List<AbstractRun> data ,String color ,int fontSize ,String fontFamily
            ,ParagraphAlignment paragraphAlignment ,TextAlignment textAlignment ,int firstLineIndent){
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(paragraphAlignment);
        paragraph.setVerticalAlignment(textAlignment);
        paragraph.setFirstLineIndent(firstLineIndent);
        fillData(paragraph ,data ,color ,fontSize ,fontFamily);
        return paragraph;
    }

    /** 填充段落数据
     * @param paragraph
     * @param data
     */
    public static void fillData(XWPFParagraph paragraph ,List<AbstractRun> data ,String color ,int fontSize ,String fontFamily){
        if (CollectionsUtil.isNull(data)){
            return;
        }
        for (AbstractRun run : data) {
            XWPFRun xwpfRun = run.getRun(paragraph);
            xwpfRun.setColor(run.getColor());
            xwpfRun.setUnderline(run.getUnderline());
            xwpfRun.setUnderlineColor(run.getUnderlineColor());
            xwpfRun.setItalic(run.isItalic());
            xwpfRun.setBold(run.isBold());
            xwpfRun.setFontFamily(run.getFontFamily());
            xwpfRun.setVerticalAlignment(run.getVerticalAlignment());
            xwpfRun.setFontSize(run.getFontSize());
            if (run.isBreakAfter()){
                xwpfRun.addBreak();
            }
            //设置覆盖值
            if (StringUtil.isNotEmpty(color)){
                xwpfRun.setColor(color);
            }
            if (StringUtil.isNotEmpty(fontFamily)){
                xwpfRun.setFontFamily(fontFamily);
            }
            if (fontSize > 0){
                xwpfRun.setFontSize(fontSize);
            }
        }
    }


    public static XWPFTable createTable(XWPFDocument document ,AbstractTable table){
        List<? extends AbstractTableRow> rowData = table.getRowData();
        if (CollectionsUtil.isNull(rowData)){
            throw new ParameterException("rowData is null");
        }
        if (table.getColSize() <= 0){
            throw new ParameterException("colSize not initialize");
        }
        XWPFTable xwpfTable = document.createTable(table.getRowSize() ,table.getColSize());
        if (StringUtil.isNotEmpty(table.getWitdth())){
            xwpfTable.setWidth(table.getWitdth());
        }
        xwpfTable.setWidthType(TableWidthType.DXA);
        xwpfTable.setTableAlignment(table.getTableAlignment());


        TableBorder bottomBorder = table.getBottomBorder();
        xwpfTable.setBottomBorder(bottomBorder.getType() ,bottomBorder.getSize() ,bottomBorder.getSpace() ,bottomBorder.getColor());
        TableBorder leftBorder = table.getLeftBorder();
        xwpfTable.setLeftBorder(leftBorder.getType() ,leftBorder.getSize() ,leftBorder.getSpace() ,leftBorder.getColor());
        TableBorder rightBorder = table.getRightBorder();
        xwpfTable.setRightBorder(rightBorder.getType() ,rightBorder.getSize() ,rightBorder.getSpace() ,rightBorder.getColor());
        TableBorder topBorder = table.getTopBorder();
        xwpfTable.setTopBorder(topBorder.getType() ,topBorder.getSize() ,topBorder.getSpace() ,topBorder.getColor());
        TableBorder insideHBorder = table.getInsideHBorder();
        xwpfTable.setInsideHBorder(insideHBorder.getType() ,insideHBorder.getSize() ,insideHBorder.getSpace() ,insideHBorder.getColor());
        TableBorder insideVBorder = table.getInsideVBorder();
        xwpfTable.setInsideVBorder(insideVBorder.getType() ,insideVBorder.getSize() ,insideVBorder.getSpace() ,insideVBorder.getColor());
        // 填充数据
        List<XWPFTableRow> rowList = xwpfTable.getRows();
        rowList.get(0).setRepeatHeader(true);
        for (int i = 0; i < rowList.size(); i++) {
            XWPFTableRow xwpfTableRow = rowList.get(i);
            AbstractTableRow row = rowData.get(i);
            row.fillToRow(xwpfTableRow);
            xwpfTableRow.setRepeatHeader(row.isRepeatHeader());
            if (row.getHeight() > 0){
                xwpfTableRow.setHeight(row.getHeight());
            }
        }
        return xwpfTable;
    }


}
