package com.gensen.tool.word.test;

import com.gensen.tool.word.model.runs.AbstractRun;
import com.gensen.tool.word.model.runs.picture.PictureRun;
import com.gensen.tool.word.model.runs.text.HyperlinkRun;
import com.gensen.tool.word.model.runs.text.TextRun;
import com.gensen.tool.word.model.table.AbstractTableRow;
import com.gensen.tool.word.model.table.BaseTable;
import com.gensen.tool.word.model.table.border.TableBorder;
import com.gensen.tool.word.test.vo.TableRow;
import com.gensen.tool.word.test.vo.TableTitleRow;
import com.gensen.tool.word.utils.xwpf.XWPFHelperUtil;
import com.gensen.tool.word.utils.xwpf.XWPFWordDocUtil;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.TypeReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gensen.Lee
 */
public class ExportMainTest {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        XWPFDocument xwpfDocument = XWPFWordDocUtil.creteDocument();;
        List<AbstractRun> data1 = new ArrayList<>();
        TextRun title = new TextRun();
        title.setText("大标题");
        title.setBold(true);
        title.setColor("999999");
        title.setFontSize(50);
        title.setUnderline(UnderlinePatterns.DOT_DASH);
        title.setFontFamily("黑体");
        title.setBreakAfter(true);
        title.setUnderlineColor("FF0000");
        data1.add(title);
        XWPFWordDocUtil.createParagraph(xwpfDocument ,data1 ,ParagraphAlignment.CENTER ,TextAlignment.CENTER);


        List<AbstractRun> data = new ArrayList<>();

        TextRun paragraphTextRun1 = new TextRun();
        paragraphTextRun1.setText(Text.LONGTEXT);
//        paragraphTextRun1.setVerticalAlignment(AbstractParagraphRun.VerticalAlignment.SUBSCRIPT);
        paragraphTextRun1.setBreakAfter(true);
        data.add(paragraphTextRun1);
        TextRun paragraphTextRun2 = new TextRun();
        paragraphTextRun2.setText(Text.STR2);
        data.add(paragraphTextRun2);


//        WordDocUtil.createParagraph(xwpfDocument ,new ArrayList<AbstractRun>(){{
//            add(paragraphTextRun2);
//            add(paragraphTextRun1);
//        }} ,555);

        TextRun paragraphTextRun3 = new TextRun();
        paragraphTextRun3.setText(Text.STR3);
//        paragraphTextRun3.setVerticalAlignment(AbstractParagraphRun.VerticalAlignment.SUPERSCRIPT);
        data.add(paragraphTextRun3);

        HyperlinkRun paragraphTextRun4 = new HyperlinkRun();
        paragraphTextRun4.setUri(Text.URL);
        paragraphTextRun4.setText("链接");
//        XWPFParagraph paragraph1 = WordDocUtil.createParagraph(xwpfDocument ,new ArrayList<AbstractRun>() {{
//            add(paragraphTextRun4);
//        }});
        TextRun superRun1 = new TextRun();
        superRun1.setText("AAAAAAAAAAAA");
        TextRun superRun = new TextRun();
        superRun.setText("xxxx");
        superRun.setVerticalAlignment(AbstractRun.VerticalAlignment.SUPERSCRIPT);

        XWPFWordDocUtil.createParagraph(xwpfDocument ,new ArrayList<AbstractRun>() {{
            add(superRun1);
            add(superRun);
        }});

        PictureRun pictureRun = new PictureRun();
        pictureRun.setHeightPixel(200);
        pictureRun.setWidthPixel(200);
        pictureRun.setPicture("C:\\Users\\lgz\\Pictures\\wallpaper-2641851.png");
        pictureRun.setType(Document.PICTURE_TYPE_PNG);
        paragraphTextRun4.setFontSize(10);
        XWPFWordDocUtil.createParagraph(xwpfDocument ,new ArrayList<AbstractRun>() {{
            add(paragraphTextRun3);
            add(paragraphTextRun4);
            pictureRun.setBreakAfter(false);
            pictureRun.setBreakBefore(false);
            add(pictureRun);
            add(paragraphTextRun3);
        }});
//        PictureRun pictureRun1 = new PictureRun();
//        pictureRun1.setHeightPixel(200);
//        pictureRun1.setWidthPixel(400);
//        pictureRun1.setPicture("C:\\Users\\lgz\\Pictures\\wallpaper-2641851.png");
//        pictureRun1.setType(Document.PICTURE_TYPE_PNG);
//        XWPFParagraph paragraph = XWPFWordDocUtil.createParagraph(xwpfDocument ,new ArrayList<AbstractRun>() {{
//            add(pictureRun1);
//        }});
//        paragraph.setAlignment(ParagraphAlignment.CENTER);



        //表格标题
        TextRun titleRun = new TextRun();
        titleRun.setText("测试表格TITLE");
        titleRun.setBold(true);
        titleRun.setFontFamily("黑体");
        titleRun.setFontSize(16);
        XWPFParagraph titleparagraph = XWPFWordDocUtil.createParagraph(xwpfDocument ,new ArrayList<AbstractRun>() {{
            add(titleRun);
        }});
        titleparagraph.setAlignment(ParagraphAlignment.LEFT);
        titleparagraph.setVerticalAlignment(TextAlignment.BOTTOM);

        TableTitleRow mock = JMockData.mock(TableTitleRow.class);
        List<AbstractTableRow> rowList = new ArrayList<>();
        rowList.add(mock);
        rowList.addAll(JMockData.mock(new TypeReference<List<TableRow>>() {}));

        BaseTable table = new BaseTable();
        table.setTableAlignment(TableRowAlign.LEFT);
        TableBorder topBorder = new TableBorder();
        topBorder.setColor("FF0000");
        topBorder.setType(XWPFTable.XWPFBorderType.DOT_DASH);
        table.setTopBorder(topBorder);

        TableBorder rightBorder = new TableBorder();
        rightBorder.setType(XWPFTable.XWPFBorderType.DOUBLE_WAVE);
        rightBorder.setColor("00FF00");
        table.setRightBorder(rightBorder);

        TableBorder inserHBorder = new TableBorder();
        inserHBorder.setType(XWPFTable.XWPFBorderType.DOUBLE_WAVE);
        inserHBorder.setColor("0000FF");
        table.setInsideHBorder(inserHBorder);

        for (AbstractTableRow row : rowList) {
            row.setHeight(0);
        }
        rowList.get(1).setHeight(5000);
        table.setWitdth("auto");
        table.setColSize(5);
        table.setRowData(rowList);

        XWPFWordDocUtil.createTable(xwpfDocument ,table);




//        XWPFTableUtil.setTableWidthAndHAlign(xwpfTable ,"600" ,STJc.RIGHT);



        XWPFHelperUtil.saveDocument(xwpfDocument ,"D:\\data\\expWordTestTable.docx");
    }

}
class Text{

    public static String STR1 = "t was not immediately clear what had caused the crash or whether anyone was in the house when it was hit by the plane. \n" +
            "\n" +
            "A passenger who survived the disaster told news website Tengrinews she heard a “terrifying sound” before the plane started losing altitude.\n" +
            "\n" +
            "“The plane was flying at a tilt. Everything was like in a movie: screaming, shouting, people crying,” she said.\n" +
            "\n" +
            "Another survivor, businessman Aslan Nazaraliyev, told the newspaper Vremya the plane started shaking while gaining altitude about two minutes after take-off.\n" +
            "\n" +
            "“At some point we started falling, not vertically, but at an angle. It seemed like control over the plane had been lost,” he said.";


    public static String STR2 = "2009, all Kazakh airlines except flagship carrier Air Astana were banned from operating in the European Union because they did not meet international safety standards. The ban was lifted in 2016.";


    public static String STR3 = "对中国房地产企业来说，2019年是艰难前行的一年，也是把握机会逆势突围的一年。在“严调控、去杠杆”的大背景下，房地产行业在2019年进入新的发展阶段，这已是业界共识。";

    public static String URL = "http://house.people.com.cn/n1/2019/1227/c164220-31525252.html";


    public static String LONGTEXT = "四、变挑战为机遇:中国企业如何困境突围 以中国目前经济发展水平和企业经营状况来 看，电子商务对于传统企业是一把双刃剑。传统企 业如果不学习和发展电子商务，未来就有可能会被 淘汰。但同时这也是一个机遇，传统企业可以在互 联网网络发展的情况下，利用这个机会来调整和变 更自己的经营方式及商业模式，取得更好地发展。 但总体来说，挑战大于机遇。那么，企业如何通过 “互联网 + ”实现创新型发展呢? 笔者认为，做好以 下几个方面，尤为重要: 一是进一步转变观念，创新思维模式，用互联网 思维迎接新挑战。随着科学技术的迅速发展，特别 是信息技术的日新月异，网络时代势必会对中国的 经济乃至社会各方面造成重大影响和变革，因此我 们应该充分利用各种手段，促进全社会上上下下都 转变观念，重视网络经济特别是网络贸易的发展，构 筑有利于网络贸易发展的社会新环境。 二是加快信息化建设，提高企业的国际竞争力。 企业单靠传统方式从事经营活动已远远不能适应市 场经济的需要，上网开拓市场被国际化大公司公认\n" +
            "为成本最低、效率最高的手段。同时，互联网化可为 中小企业参与竞争提供有力武器，使其只要花很低 的成本就可以在网络上实现跨国经营。当前，中国 企业面临的最大挑战是如何利用计算机和互联网技 术对自身技术进行革命性变革，充分利用网络贸易 带来的新商机，进一步提高国际竞争力，实现企业新 型发展。 三是加强对网络贸易的研究，规范网络贸易的 发展。网络贸易是一种全新的商业模式，同时也带 来了不少新的问题。比如，交易的安全性，网络贸易 的征税问题，知识产权的保护，电子合同有效性和纠 纷处理，等等，这些问题能否解决好直接关系到网络 贸易的发展。而且由于网络贸易的发展速度很快， 业务方式没有跟踪，在发展过程中具有本身的优势， 与现有体制也有一定冲突。因此，必须对市场发展 保持高度敏感，加强对网络贸易的研究，制定完善相 应的政策标准和法规。 总之，企业应该是“互联网 + ”的载体和践行 者，互联网企业和基础电商企业应该致力打造“互 联网”生态圈，传统企业则应依据互联网的创新模 式、商业模式和生产方式、组织机构、人才结构等方 面进行深入调查，打造开创性经济形态的新优势在国家政策的推动下，中国的互联网取得了蓬 勃发展。截至 2015 年 6 月，中国网民规模达6. 68 亿，互联网普及率为 48. 8%。而且在网民中使用手 机的占比由 2014 年 12 月的 85. 8% 升至 88. 9%。 得益于移动互联网时代到来和不断地深化，中国互 联网产业又迎来了新一轮发展热潮。在这一过程 中，农村网民增长比例逐步增大，互联网应用方面搜 索引擎、新闻客户端、电子商务、 SNS、即时通信、阅 读、游戏、视频等移动端的发力，成为拉动网络经济 发展的新引擎。与互联网的飞速发展相对应，中国 的电子商务市场也将逐渐显现出两大新趋势: 一是转向农村电商。自2014 年始，国内电商巨 头纷纷打出了各自的“上山下乡”战略。进入 2015 年后，阿里和京东更是全速推进“农村电商”战略落 地。其中，阿里巴巴提出“千县万村计划”，计划在 三至五年内投资100 亿元，建立1000 个县级运营中 心和 10 万个村级服务站。京东则提出“3F 战略”， 即工业品进农村战略( Factory to country) 、农村金融 战略( Finance to Country) 和生鲜电商战略( Farm to Table) 。 此外，其他中国电商巨头也纷纷施展“招数”进 军农村市场。如中国主流电商企业之一的苏宁易购 即利用“双11”、“双12”等购物狂欢季，进一步将原 有的数百家乡镇服务点升级为新式乡村服务站。这 些服务站可为农民提供代客下单、最后一公里配送 及售后维修等服务。苏宁易购还制定了一份长远计 划，那就是在未来五年内，建设1 万家苏宁易购服务 站，覆盖中国1/4 的乡镇。 刺激电商巨头发力农村电子商务的是中国农村 地区巨大的消费潜力。阿里研究院 2014 年发布的 消费报告预测， 2014 年全国农村网购市场规模将达 到1800 亿元，到2016 年将有望增长到 4600 亿元规 模。而在过去三年，淘宝农村消费占比不断提升，从 2012 年第二季度的7. 11%上升至2014 年第一季度 的9. 11%。 二是转向跨境电商。马云曾披露阿里巴巴未来 十年的愿景———帮助全球 20 亿消费者在线购买全 世界的产品。2015 年 6 月 24 日，继 5 月宣布启动 首个国家馆韩国馆之后，阿里巴巴集团旗下聚划算 平台和天猫国际联合开启“地球村”模式。美国、英 国、法国、西班牙、瑞士、澳大利亚、新西兰、新加坡、 泰国、马来西亚、土耳其等 11 国国家馆在天猫国际\n" +
            "亮相。同日，阿里巴巴聚划算平台宣布全面启动与 20 国国家大使馆合作进程。阿里巴巴中国零售事 业群总裁张建锋表示 : “在中国，电商的渗透率只有 约50%，而在韩国和美国，这个数字可以达到90%， 可以看出，中国电商还有非常大的增长潜力，未来几 年也将保持高速增长。同时，中国日益壮大的中产 阶级消费能力强劲，对优质产品的需求旺盛，海外优 质产品在中国具有非常广阔的市场前景。”作为中 国电商企业的领头羊，阿里巴巴在跨境电商的布局 和扩张，说明中国电商巨头进军海外市场的扩张计 划是非常巨大的。 在此情况下，中国应该加强与东盟各个国家之 间的合作。一方面，继续维护彼此之间的传统贸易 合作;另一方面，则应进一步共享科技进步的丰硕成 果，发展和打造东盟国家之间的跨境电商以及其他 “互联网 + ”产业。中国和东盟国家本来就有很好 的合作基础，如果合理打造跨境电商和其他“互联 网 + ”产业，会使得东盟国家之间的合作更有活力， 也更有动力。 三、年轻人 : “互联网 + ”的主力军 年轻人既是“互联网 + ”服务的对象，也是“互 联网 + ”的实际参与者，所以一定要吸引年轻人参 与到“互联网 + ”和创新创业活动中来，既要为他们 服务，又要使他们发挥优势为“互联网 + ”创造新的 发展和经验。因为 80 后、 90 后，甚至 00 后的年轻 人才是“互联网 + ”的主力军。 年轻人思想活跃，创新观念比较多，代表着未 来，代表着方向。尤其是年轻一代大学生，对其创新 创业问题，更应高度重视。一方面，高校应鼓励大学 生创业，以更加宽容的氛围帮助其在创新创业实践 中进行锻炼;另一方面，社会各界也要有包容失败的 氛围，引导大学生从失败中汲取经验教训。可以想 见，如果年轻人积极参加发展电子商务，电子商务将 会发展得更好。 二是行业的快速健康发展，离不开人才培养。 而电子商务的人才培养，显然应以年轻人为主力。 当然，政府在制定互联网和电子商务人才培养计划 时应两条腿走路: 一方面，在中等、高等和职业院校 里都设置相应专业，多招学生，不要受学历所限，多 让他们学一些; 另一方面，不要完全拘泥于学习教 育，要通过实践让年轻人在企业里面学会互联网操 作，学会如何更好地发展和更快地进步。此外，还可 制定一些专门针对年轻人的政策。比如，如果他们 掌握了互联网和电子商务的技术，可同样成为工2015 年 5 月，国务院在《关于大力发展电子商 务加快培育经济新动力的意见》中明确指出，电子 商务业已成为中国经济发展新的原动力，不仅创造 了新的消费需求，引发了新的投资热潮，开辟了就业 增收新渠道，为大众创业、万众创新提供了新空间， 而且电子商务还正加速与制造业融合，进而推动制 造业和服务业转型升级，催生新兴业态，成为提供公 共产品、公共服务的新力量。国家对于电子商务发 展的判断无疑是准确的，但处于这样一个信息化时 代，在国内国际经济社会发展瞬息万变的情况下，中 国电子商务如何更好地适应发展趋势，并未雨绸缪 顺势而上引领时代潮流，仍值得深入思考。 一、“互联网+” :中国电子商务发展的新引擎 第三次科技革命以原子能、电子计算机及信息 技术、空间技术和生物工程的发明与应用为主要标 志，是人类文明史上继蒸汽技术革命和电力技术革 命之后科技领域里的又一次重大飞跃，极大地推动 了人类社会经济、政治、文化领域的变革，更加影响 了人类的生产方式、生活方式和思维方式。改革开 放之后，中国及时抓住了第三次科技革命的机遇，积 极推进工业化与信息化深度融合。由此，中国开始 步入科技不断进步、经济高速增长的新时期，从经济 比较落后的国家，成为仅次于美国的全球第二大经 济体。\n" +
            "当前，随着信息化和工业化的深度融，第四次产 业革命已经到来。在这样的背景下，“互联网 + ”的 发展趋势不可阻挡，零售、交通、通信、医疗、教育等 行业都正在通过“互联网 + ”实现创新型发展。一 时间，“互联网 + ”成为社会关注的焦点。与此相适 应， 2015 年中国政府工作报告明确提出，加快研究 制定“互联网 + ”行动计划，并将“互联网 + ”上升为 国家战略来推动。这表明，互联网不再是一个简单 的工具，而成为一种新形态。大力推广应用互联网， 尤其是推动互联网与农业、制造业、文化、教育、医疗 等传统产业的结合，可以大大提高生产力和经济效 益，形成新业态、新模式。互联网将对经济发展起到 更多作用，是经济企稳回升的重要推动因素。由此 可以预测，中国的电子商务和各个互联网经济必将 有更大的发展。这主要是基于以下两点: 一是电子 商务和一切“互联网 + ”是现代科技进步的产物，是 新的经济形态，本身有其不可替代的优越性。二是 两者都受到中央和各级政府的高度重视及支持。其 实，互联网在支撑大众创业、大众创新中的作用早已 显现，并业已成为提供公共服务的重要手段。而国 家提出“互联网 + ”的意图也极为明显，就是要推动 经济转型升级，让“互联网 + ”落地生根，开创经济 新时代。由此来看，“互联网 + ”与电子商务的结合 必将引发经济运行模式变革和优化";

}