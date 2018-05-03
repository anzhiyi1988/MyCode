package com.thunisoft.data.quality;

import com.alibaba.fastjson.JSON;
import com.thunisoft.data.quality.bean.Result;
import com.thunisoft.data.quality.conn.DBManager;
import com.thunisoft.data.quality.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Runner {


    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);
    private static final Map<String, String> oneStageCache = new HashMap<>();
    private static final Map<String, String> twoStageCache = new HashMap<>();
    private static final List<Result> results = new ArrayList<>();

    void go() {


        String file;
        try {
            URL url = Runner.class.getClassLoader().getResource("数据分析.xlsx");
            if (url != null) {
                file = URLDecoder.decode(url.getFile(), "utf-8");
            } else {
                throw new Exception("找不到文件 数据分析.xlsx");
            }

        } catch (Exception e) {
            LOG.error("解析excel失败", e);
            return;
        }


        boolean flag = initCache(file);
        if (!flag) {
            LOG.error("init cache error , place check !");
            return;
        }

        flag = buildSQL();
        if (!flag) {
            LOG.error("build sql error , place check !");
            return;
        }

        flag = runSQL();
        if (!flag) {
            LOG.error("run sql error , place check !");
        }


    }

    private boolean runSQL() {

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://172.23.21.19:5432/db_ysk?currentSchema=public&Charset=utf8";
        String user = "gpadmin";
        String password = "123456";

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getConn(driver, url, user, password);
            stat = conn.createStatement();

            for (Result result : results) {

                String sql = result.getOneSql();

                try {
                    rs = stat.executeQuery(sql);
                    while (rs.next()) {

                        String s = rs.getString(1);
                        int n = rs.getInt(2);

                        if ("null".equals(s)) {
                            result.setNullCount(n);
                        } else if ("\"\"".equals(s)) {
                            result.setBlankCount(n);
                        }
                    }
                    LOG.info(JSON.toJSONString(result));
                } catch (SQLException e) {
                    LOG.error("执行sql失败" + sql, e);
                } finally {
                    DBManager.close(rs);
                }
            }
            return true;
        } catch (Exception e) {
            LOG.error("建立数据库或者建立statement失败，url：" + url, e);
            return false;
        } finally {
            DBManager.close(stat, conn);
        }
    }


    /**
     * @param file 文件路径
     * @return boolean
     */
    private boolean initCache(String file) {
        Workbook workBook = ExcelUtil.getWorkBook(file);
        if (workBook == null) {
            return false;
        }

        try {

            List<Map<Integer, String>> oneStageSqlData = getOneStageSqlData(workBook);
            for (Map<Integer, String> map : oneStageSqlData) {
                String sqlKey = map.get(0).toLowerCase();
                String sql = map.get(2).toLowerCase();
                oneStageCache.put(sqlKey, sql);
            }
            oneStageSqlData.clear();


            List<Map<Integer, String>> twoStageSqlData = getTwoStageSqlData(workBook);
            for (Map<Integer, String> map : twoStageSqlData) {
                String sqlKey = map.get(0).toLowerCase();
                String sql = map.get(2).toLowerCase();
                twoStageCache.put(sqlKey, sql);
            }
            twoStageSqlData.clear();


            List<Map<Integer, String>> colData = getColData(workBook);
            for (Map<Integer, String> map : colData) {
                String table = map.get(0).toLowerCase();
                String column = map.get(1).toLowerCase();
                String colName = map.get(2);

                String oneSqlKey = map.get(11).toLowerCase();
                if (StringUtils.isBlank(oneSqlKey)) {
                    continue;
                }

                String twoSqlKey = map.get(13).toLowerCase();


                Result result = new Result();
                result.setTable(table);
                result.setColumn(column);
                result.setColName(colName);
                result.setOneSqlKey(oneSqlKey);
                result.setTwoSqlKey(twoSqlKey);
                results.add(result);
            }
            colData.clear();

            return true;
        } catch (Exception e) {
            LOG.error("解析excel异常：" + file, e);
            return false;
        }
    }


    /**
     * 为每个字段建立sql
     *
     * @return boolean
     */
    private boolean buildSQL() {

        for (Result result : results) {

            String table = result.getTable();
            String column = result.getColumn();
            String oneSqlKey = result.getOneSqlKey();
            String twoSqlKey = result.getTwoSqlKey();

            String oneSql = oneStageCache.get(oneSqlKey);
            oneSql = oneSql.replace("[table]", table);
            oneSql = oneSql.replace("[column]", column);
            result.setOneSql(oneSql);

            String twoSql = twoStageCache.get(twoSqlKey);
            if (StringUtils.isNotBlank(twoSql)) {
                twoSql = twoSql.replace("[table]", table);
                twoSql = twoSql.replace("[column]", column);
                result.setTwoSql(twoSql);
            }


        }


        return true;
    }

    private List<Map<Integer, String>> getTwoStageSqlData(Workbook workBook) throws Exception {
        int sheetIndex = 3;
        int sRow = 0;
        int eRow = 1;
        int sCol = 0;
        int eCol = 2;
        return ExcelUtil.getDataFromExcel(
                workBook, sheetIndex, sRow, eRow, sCol, eCol);
    }

    private List<Map<Integer, String>> getOneStageSqlData(Workbook workBook) throws Exception {
        int sheetIndex = 2;
        int sRow = 0;
        int eRow = 1;
        int sCol = 0;
        int eCol = 2;
        return ExcelUtil.getDataFromExcel(
                workBook, sheetIndex, sRow, eRow, sCol, eCol);
    }


    private List<Map<Integer, String>> getColData(Workbook workBook) throws Exception {
        int sheetIndex = 1;
        int sRow = 3;
        int eRow = 2627;
        int sCol = 0;
        int eCol = 14;
        return ExcelUtil.getDataFromExcel(
                workBook, sheetIndex, sRow, eRow, sCol, eCol);
    }
}
