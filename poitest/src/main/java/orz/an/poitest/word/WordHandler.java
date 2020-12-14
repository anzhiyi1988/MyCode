package orz.an.poitest.word;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class WordHandler {

    /**
     * 在使用XWPFWordExtractor读取docx文档的内容时，我们只能获取到其文本，而不能获取到其文本对应的属性值。
     */
    public String readByExtractor(String file) {

        try (XWPFWordExtractor xwe = new XWPFWordExtractor(new XWPFDocument(new FileInputStream(file)))) {
            return xwe.getText();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return "error";
    }

    /**
     * document 可以分项获取内容，此处是获取每个段落的内容。
     *
     * @param file 整个文件的路径
     * @return string
     */
    public String getParagraphs(String file) {
        try (XWPFDocument xd = new XWPFDocument(new FileInputStream(file))) {
            List<XWPFParagraph> ps = xd.getParagraphs();
            int i = 0;
            for (XWPFParagraph p : ps) {
                log.info("XWPFParagraph : " + i);
                log.info(p.getText());
                i++;
            }


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * document 可以分项获取内容，此处是获取每个段落的内容。
     *
     * @param file 文件
     * @return 返回什么我也不知道
     */
    public String getTables(String file) {
        try (XWPFDocument xd = new XWPFDocument(new FileInputStream(file))) {
            List<XWPFTable> xt = xd.getTables();
            xt.forEach(table -> table.getRows().forEach(row -> row.getTableCells().forEach(cell -> log.info(cell.getText()))));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    public Map<String, List<String[]>> getTableData(String file) {
        try (XWPFDocument xd = new XWPFDocument(new FileInputStream(file))) {
            List<IBodyElement> es = xd.getBodyElements();
            String tableName = StringUtils.EMPTY;
            Map<String, List<String[]>> map = new HashMap<>();
            for (IBodyElement e : es) {
                if (e instanceof XWPFParagraph) {
                    tableName = ((XWPFParagraph) e).getText();
                }
                if (e instanceof XWPFTable && StringUtils.isNotEmpty(tableName) && !"数据类型定义".equals(tableName)) {
                    log.info("----------------------------------------");
                    XWPFTable table = ((XWPFTable) e);
                    List<XWPFTableRow> rows = table.getRows();
                    log.info(tableName);
                    List<String[]> newRows = new ArrayList<>();
                    for (int i = 1; i < rows.size(); i++) {
                        XWPFTableRow row = rows.get(i);
                        String[] cells = new String[5];
                        int[] j = {0};
                        row.getTableCells().forEach(cell -> cells[j[0]++] = cell.getText());
                        newRows.add(cells);
                    }
                    map.put(tableName, newRows);
                }
            }
            log.debug(JSON.toJSONString(map));
            return map;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
