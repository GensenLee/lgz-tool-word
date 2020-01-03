package com.gensen.tool.word.model.runs.text;

import com.gensen.tool.word.model.runs.AbstractRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/** 文本
 * @author Gensen.Lee
 * @date 2019/12/27 14:54
 */
public class TextRun extends AbstractRun {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public XWPFRun getRun(XWPFParagraph paragraph) {
        XWPFRun run = paragraph.createRun();
        run.setText(this.text);
        return run;
    }
}
