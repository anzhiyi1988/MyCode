package orz.an.poitest;

import orz.an.poitest.word.WordHandler;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


        String file = "G:/recvfile/曹珍/检举举报技术规范_发项目组V3.6.0415（修改信访室确认意见）/数据格式技术规范/检举举报大数据应用子平台接入数据格式规范 第2部分：监督对象数据v3.3.0324.docx";

        WordHandler wh = new WordHandler();
        // String content = wh.readByExtractor(file);
        // String content = wh.getParagraphs(file);
        String content = wh.getTables(file);
        System.out.println(content);
    }


}
