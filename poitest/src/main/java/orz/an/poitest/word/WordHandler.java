package orz.an.poitest.word;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordHandler {


    /**
     * 在使用XWPFWordExtractor读取docx文档的内容时，我们只能获取到其文本，而不能获取到其文本对应的属性值。
     */
    public String readByExtractor(String file) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            XWPFDocument xd = new XWPFDocument(is);
            XWPFWordExtractor xwe = new XWPFWordExtractor(xd);

            return xwe.getText();
        } catch (FileNotFoundException e) {
            System.out.println("没有对应文: " + file);
        } catch (IOException e) {
            System.out.println("文件读取有问题");
        } finally {
            close(is);
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
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            XWPFDocument xd = new XWPFDocument(is);
            List<XWPFParagraph> ps = xd.getParagraphs();
            int i = 0;
            for (XWPFParagraph p : ps) {
                System.out.println("XWPFParagraph : " + i);
                System.out.println(p.getText());
                i++;
            }


        } catch (FileNotFoundException e) {
            System.out.println("没有对应文: " + file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(is);
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
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            XWPFDocument xd = new XWPFDocument(is);
            List<XWPFTable> xt = xd.getTables();
            xt.forEach(table -> table.getRows().forEach(row -> row.getTableCells().forEach(cell -> System.out.println(cell.getText()))));
        } catch (FileNotFoundException e) {
            System.out.println("没有对应文: " + file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(is);
        }
        return null;
    }


    public Map<String, List<String[]>> getTableData(String file) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            XWPFDocument xd = new XWPFDocument(is);
            List<IBodyElement> es = xd.getBodyElements();
            String tableName = StringUtils.EMPTY;
            Map<String, List<String[]>> map = new HashMap<>();
            for (IBodyElement e : es) {
                if (e instanceof XWPFParagraph) {
                    tableName = ((XWPFParagraph) e).getText();
                }
                if (e instanceof XWPFTable && StringUtils.isNotEmpty(tableName) && !"数据类型定义".equals(tableName)) {
                    System.out.println("----------------------------------------");
                    XWPFTable table = ((XWPFTable) e);
                    List<XWPFTableRow> rows = table.getRows();
                    System.out.println(tableName);
                    List<String[]> _rows = new ArrayList<>();
                    for (int i = 1; i < rows.size(); i++) {
                        XWPFTableRow row = rows.get(i);
                        String[] cells = new String[5];
                        int[] j = {0};
                        row.getTableCells().forEach(cell -> cells[j[0]++] = cell.getText());
                        _rows.add(cells);
                    }
                    map.put(tableName, _rows);
                }
            }
            return map;
        } catch (FileNotFoundException e) {
            System.out.println("没有对应文: " + file);
        } catch (IOException e) {
            System.out.println("文件读取错误");
        } finally {
            close(is);
        }
        return null;
    }

    private void close(InputStream is) {
        try {
            if (is != null) is.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
