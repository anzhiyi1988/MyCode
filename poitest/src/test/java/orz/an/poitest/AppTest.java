package orz.an.poitest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import orz.an.poitest.word.JsonHandler;
import orz.an.poitest.word.WordHandler;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {

    final String file = "G:/recvfile/曹珍/检举举报技术规范_发项目组V3.6.0415（修改信访室确认意见）/数据格式技术规范/检举举报大数据应用子平台接入数据格式规范 第2部分：监督对象数据v3.3.0324.docx";


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void testExtractor() {
        WordHandler wh = new WordHandler();
        String content = wh.readByExtractor(file);
        assertNotNull(content);
    }

    @Test
    public void testParagraphs() {
        WordHandler wh = new WordHandler();
        String content = wh.getParagraphs(file);
        assertNotNull(content);

    }

    @Test
    public void testTables() {
        WordHandler wh = new WordHandler();
        String content = wh.getTables(file);
        assertNotNull(content);
    }

    @Test
    public void testElements() {
        WordHandler wh = new WordHandler();
        Map<String, List<String[]>> content = wh.getTableData(file);
        assertNotNull(content);
    }

    @Test
    public void testWriteJson() {
        WordHandler wh = new WordHandler();
        JsonHandler jh = new JsonHandler();
        Map<String, List<String[]>> content = wh.getTableData(file);
        jh.parseData2Json(content);
        assertNotNull(content);
    }

    @Test
    public void testSB() {
       StringBuffer sb = new StringBuffer("10");
        sb.append("000000000000000");
        String a = sb.substring(0,12);


        System.out.println(sb.toString());
        System.out.println(a);

    }

}
