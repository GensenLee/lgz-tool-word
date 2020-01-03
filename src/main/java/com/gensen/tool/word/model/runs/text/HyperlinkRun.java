package com.gensen.tool.word.model.runs.text;

import com.gensen.tool.word.model.runs.AbstractRun;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/** 超链接
 * @author Gensen.Lee
 * @date 2019/12/27 14:54
 */
public class HyperlinkRun extends AbstractRun {

    private String uri;

    private String text;

    public HyperlinkRun() {
        setColor("0000FF");
        setItalic(true);
        setUnderline(UnderlinePatterns.SINGLE);
        setUnderlineColor("0000FF");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public XWPFRun getRun(XWPFParagraph paragraph) {
        XWPFHyperlinkRun hyperlinkRun = paragraph.createHyperlinkRun(uri);
        hyperlinkRun.setText(this.text);
        return hyperlinkRun;
    }
}
