package orz.an.poitest;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import orz.an.poitest.word.WordHandler;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
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
        System.out.println(content);
    }

    @Test
    public void testParagraphs() {
        WordHandler wh = new WordHandler();
        String content = wh.getParagraphs(file);
        System.out.println(content);

    }

    @Test
    public void testTables() {
        WordHandler wh = new WordHandler();
        String content = wh.getTables(file);
        System.out.println(content);
    }

    @Test
    public void testElements() {
        WordHandler wh = new WordHandler();
        Map<String, List<String[]>> content = wh.getTableData(file);
        System.out.println(JSON.toJSONString(content));

    }
}
