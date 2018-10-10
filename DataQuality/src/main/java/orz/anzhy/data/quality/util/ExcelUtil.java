package orz.anzhy.data.quality.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);


    public static Workbook getWorkBook(String excel) {
        InputStream ins = null;
        try {
            ins = new FileInputStream(new File(excel));
            return WorkbookFactory.create(ins);
        } catch (Exception e) {
            LOG.error("找不到指定文件：" + excel, e);
            return null;
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
            } catch (IOException e) {
                LOG.warn("IO关闭异常");
            }
        }
    }

    /**
     * 读取excel，读出来的是一个数据矩阵。
     *
     * @param wb         文件
     * @param sheetIndex 读取的sheet页，从0开始
     * @param sRow       开始行，从0开始
     * @param eRow       结束行
     * @param sCol       开始列，从0开始
     * @param eCol       结束列
     * @return all rows are stored in list, each row is stored with map , the key of map is column number.
     * @throws Exception 读取异常
     */
    public static List<Map<Integer, String>> getDataFromExcel(Workbook wb,
                                                              int sheetIndex, int sRow, int eRow, int sCol, int eCol) throws Exception {
        if (sCol > eCol) {
            throw new Exception("结束列小于开始列，你闹呢！！！");
        }
        Sheet sheet = wb.getSheetAt(sheetIndex);

        if (sRow > eRow) {
            throw new Exception("结束行小于开始行，你闹呢！！！");
        }

        List<Map<Integer, String>> data = new ArrayList<>();
        for (int i = sRow; i <= eRow; i++) {
            Row row = sheet.getRow(i);
            Cell cell;
            try {
                 cell = row.getCell(0);
            } catch (Exception e) {
                LOG.info(i+"");
                throw e;
            }

            String value = getStringValue(cell);
            // 如果是"----"此行不遍历
            if ("----".equals(value)) {
                continue;
            }

            Map<Integer, String> map = new HashMap<>();
            for (int j = sCol; j <= eCol; j++) {
                Cell cell1 = row.getCell(j);
                Integer key1 = j;
                String value1 = getStringValue(cell1);
                map.put(key1, value1);
            }
            data.add(map);
        }
        return data;
    }


    /**
     * 无论单元格内是什么类型，都转换为string类型返回。
     *
     * @param cell 单元格
     * @return 单元格的值
     */
    private static String getStringValue(Cell cell) {
        String value = StringUtils.EMPTY;
        if (cell == null) {
            return value;
        }
        int type = cell.getCellType();

        switch (type) {
            case Cell.CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case Cell.CELL_TYPE_ERROR:
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getNumericCellValue()).trim();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                double numericCellValue = cell.getNumericCellValue();
                value = String.valueOf((int) numericCellValue).trim();
                break;
            case Cell.CELL_TYPE_STRING:
                value = String.valueOf(cell.getStringCellValue()).trim();
                break;
            default:
                break;
        }
        return value;
    }

}
