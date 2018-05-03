package com.thunisoft.data.quality.bean;

public class Result {

    private String table;
    private String column;
    private String colName;
    private String oneSqlKey;
    private String twoSqlKey;
    private String oneSql;
    private String twoSql;
    private int nullCount;
    private int blankCount;

    public int getNullCount() {
        return nullCount;
    }

    public void setNullCount(int nullCount) {
        this.nullCount = nullCount;
    }

    public int getBlankCount() {
        return blankCount;
    }

    public void setBlankCount(int blankCount) {
        this.blankCount = blankCount;
    }

    public String getOneSql() {
        return oneSql;
    }

    public void setOneSql(String oneSql) {
        this.oneSql = oneSql;
    }

    public String getTwoSql() {
        return twoSql;
    }

    public void setTwoSql(String twoSql) {
        this.twoSql = twoSql;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getOneSqlKey() {
        return oneSqlKey;
    }

    public void setOneSqlKey(String oneSqlKey) {
        this.oneSqlKey = oneSqlKey;
    }

    public String getTwoSqlKey() {
        return twoSqlKey;
    }

    public void setTwoSqlKey(String twoSqlKey) {
        this.twoSqlKey = twoSqlKey;
    }

}
