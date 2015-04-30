package com.cshuig.model;

import com.cshuig.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshuig on 15/4/26.
 */
public class Table {

    private String tableName;

    private String tableComment;

    private String packageName;

    private String className;

    private List<Column> columnList;

    public List<Column> getColumnList() {
        if (columnList == null) {
            columnList = new ArrayList<>();
        }
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.setClassName(StringUtils.upperFirestChar(StringUtils.formatField(this.getTableName().toLowerCase())));
    }
}
