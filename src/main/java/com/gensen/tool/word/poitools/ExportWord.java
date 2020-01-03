package com.gensen.tool.word.poitools;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @Description 导出word文档
 * @Author Huangxiaocong
 * 2018年12月1日  下午12:12:15
 */
public class ExportWord {
    private XWPFHelperTable xwpfTableTool = null;
    private XWPFHelper xwpfHelper = null;

    public ExportWord() {
        xwpfTableTool = new XWPFHelperTable();
        xwpfHelper = new XWPFHelper();
    }

    /**
     * 创建好文档的基本 标题，表格  段落等部分
     *
     * @return
     */
    public XWPFDocument createXWPFDocument(int row ,int col) {
        XWPFDocument doc = new XWPFDocument();
        createTitleParagraph(doc);
        createTableParagraph(doc ,row ,col);
        return doc;
    }

    public XWPFDocument createXWPFDocument(XWPFDocument doc ,int row ,int col) {
        createTitleParagraph(doc);
        createTitleCommentParagraph(doc);
        createTableParagraph(doc ,row ,col);
        return doc;
    }

    public XWPFDocument createXWPFDocument(XWPFDocument doc ,int row ,int col ,List<String> widths) {
        createTitleParagraph(doc);
        createTitleCommentParagraph(doc);
        createTableParagraph(doc ,row ,col ,widths);
        return doc;
    }

    /**
     * 创建表格的标题样式
     *
     * @param document
     */
    public void createTitleParagraph(XWPFDocument document) {
        XWPFParagraph titleParagraph = document.createParagraph();    //新建一个标题段落对象（就是一段文字）
        titleParagraph.setAlignment(ParagraphAlignment.LEFT);//样式居中
        XWPFRun titleFun = titleParagraph.createRun();    //创建文本对象
//        titleFun.setText(titleName); //设置标题的名字
        titleFun.setBold(true); //加粗
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(16);    //字体大小
        titleFun.setFontFamily("黑体");//设置字体

        //...
        titleFun.addBreak();    //换行
    }

    /** 表注释
     * @param document
     */
    public void createTitleCommentParagraph(XWPFDocument document) {
        XWPFParagraph titleParagraph = document.createParagraph();    //新建一个标题段落对象（就是一段文字）
        titleParagraph.setAlignment(ParagraphAlignment.LEFT);//样式居中
        XWPFRun titleFun = titleParagraph.createRun();    //创建文本对象
//        titleFun.setBold(true); //加粗
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(12);    //字体大小
        titleFun.setFontFamily("宋体");//设置字体

        //...
//        titleFun.addBreak();    //换行
    }

    /**
     * 设置表格
     *
     * @param document
     * @param rows
     * @param cols
     */
    public void createTableParagraph(XWPFDocument document ,int rows ,int cols ,List<String> colWidth) {
        XWPFTable infoTable = document.createTable(rows ,cols);
        xwpfTableTool.setTableWidthAndHAlign(infoTable ,"9072" ,STJc.CENTER);
        //合并表格
//        xwpfHelperTable.mergeCellsHorizontal(infoTable ,1 ,1 ,5);
//        xwpfHelperTable.mergeCellsVertically(infoTable ,0 ,3 ,6);
//        for (int col = 3; col < 7; col++) {
//            xwpfHelperTable.mergeCellsHorizontal(infoTable ,col ,1 ,5);
//        }
        //设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        // 表头
        XWPFTableRow itr = rowList.get(0);
        setCellWidth(itr ,colWidth);
        List<XWPFTableCell> cellListOut = itr.getTableCells();
        for (int j = 0; j < cellListOut.size(); j++) {
            XWPFParagraph cellParagraph = cellListOut.get(j).getParagraphArray(0);
            cellParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun cellParagraphRun = cellParagraph.createRun();
            cellParagraphRun.setFontSize(13);
            cellParagraphRun.setBold(true);
        }
        for (int i = 1; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for (int j = 0; j < cellList.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                cellParagraph.setWordWrapped(true);
                cellParagraph.setAlignment(ParagraphAlignment.LEFT);
                cellParagraph.setVerticalAlignment(TextAlignment.CENTER);
                XWPFRun cellParagraphRun = cellParagraph.createRun();
                cellParagraphRun.setFontSize(12);
            }
        }
        xwpfTableTool.setTableHeight(infoTable ,560 ,STVerticalJc.CENTER);
    }

    public void createTableParagraph(XWPFDocument document ,int rows ,int cols) {
        XWPFTable infoTable = document.createTable(rows ,cols);
        xwpfTableTool.setTableWidthAndHAlign(infoTable ,"9072" ,STJc.CENTER);
        //合并表格
//        xwpfHelperTable.mergeCellsHorizontal(infoTable ,1 ,1 ,5);
//        xwpfHelperTable.mergeCellsVertically(infoTable ,0 ,3 ,6);
//        for (int col = 3; col < 7; col++) {
//            xwpfHelperTable.mergeCellsHorizontal(infoTable ,col ,1 ,5);
//        }
        //设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        // 表头
        XWPFTableRow itr = rowList.get(0);
        List<XWPFTableCell> cellListOut = itr.getTableCells();
        for (int j = 0; j < cellListOut.size(); j++) {
            XWPFParagraph cellParagraph = cellListOut.get(j).getParagraphArray(0);
            cellParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun cellParagraphRun = cellParagraph.createRun();
            cellParagraphRun.setFontSize(13);
            cellParagraphRun.setBold(true);
        }
        for (int i = 1; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for (int j = 0; j < cellList.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                cellParagraph.setAlignment(ParagraphAlignment.LEFT);
                cellParagraph.setVerticalAlignment(TextAlignment.CENTER);
                XWPFRun cellParagraphRun = cellParagraph.createRun();
                cellParagraphRun.setFontSize(12);
            }
        }
        xwpfTableTool.setTableHeight(infoTable ,560 ,STVerticalJc.CENTER);
    }

    /**
     * 往表格中填充数据
     *
     * @param dataList
     * @param document
     * @param savePath
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void exportCheckWord(Map<String, Object> dataList ,XWPFDocument document ,String savePath) throws IOException {
        XWPFParagraph paragraph = document.getParagraphArray(0);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun titleFun = paragraph.getRuns().get(0);
        titleFun.setText(String.valueOf(dataList.get("TITLE")));
        List<List<Object>> tableData = (List<List<Object>>) dataList.get("TABLEDATA");
        XWPFTable table = document.getTableArray(0);
        fillTableData(table ,tableData);
        xwpfHelper.saveDocument(document ,savePath);
    }

    @SuppressWarnings("unchecked")
    public void exportCheckWord(Map<String, Object> dataList ,XWPFDocument document ,int tableIndex) throws IOException {
        // 表名
        XWPFParagraph paragraph = document.getParagraphArray(tableIndex * 2);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun titleFun = paragraph.getRuns().get(0);
        titleFun.setText(String.valueOf(dataList.get("TITLE")));
        // 表注释
        paragraph = document.getParagraphArray(tableIndex * 2 + 1);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        titleFun = paragraph.getRuns().get(0);
        titleFun.setText(String.valueOf(dataList.get("COMMENT")));

        List<List<Object>> tableData = (List<List<Object>>) dataList.get("TABLEDATA");
        XWPFTable table = document.getTableArray(tableIndex);
        fillTableData(table ,tableData);
    }

    @SuppressWarnings("unchecked")
    public void exportCheckWord(List<Map<String, Object>> list ,XWPFDocument document) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            XWPFParagraph paragraph = document.getParagraphArray(i);
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun titleFun = paragraph.getRuns().get(0);
            titleFun.setText(String.valueOf(list.get(i).get("TITLE")));
            List<List<Object>> tableData = (List<List<Object>>) list.get(i).get("TABLEDATA");
            XWPFTable table = document.getTableArray(i);
            fillTableData(table ,tableData);
        }
    }

    /**
     * 往表格中填充数据
     *
     * @param table
     * @param tableData
     */
    public void fillTableData(XWPFTable table ,List<List<Object>> tableData) {
        List<XWPFTableRow> rowList = table.getRows();
        for (int i = 0; i < tableData.size(); i++) {
            List<Object> list = tableData.get(i);
            List<XWPFTableCell> cellList = rowList.get(i).getTableCells();
            for (int j = 0; j < list.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                XWPFRun cellParagraphRun = cellParagraph.getRuns().get(0);
                cellParagraphRun.setText(String.valueOf(list.get(j)));
            }
        }
    }

    public void setCellWidth(XWPFTableRow itr ,List<String> colWidth){
        List<XWPFTableCell> tableCells = itr.getTableCells();
        // 设置列宽
        for (int i = 0; i < tableCells.size(); i++) {
            XWPFTableCell tableCell = tableCells.get(i);
            CTTc ctTc = tableCell.getCTTc();
            CTTcPr tcpr = ctTc.addNewTcPr();
            CTTblWidth tblWidth = tcpr.isSetTcW() ? tcpr.getTcW() : tcpr.addNewTcW();
            if (colWidth.size() > i){
                tblWidth.setW(new BigInteger(colWidth.get(i)));
            }
            tblWidth.setType(STTblWidth.DXA);
        }
    }
}