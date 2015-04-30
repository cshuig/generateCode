package com.cshuig.model;

import com.cshuig.utils.StringUtils;

/**
 * Created by cshuig on 15/4/26.
 */
public class Column {

    private String columnName;                          //列名

    private String columnType;                          //列的类型

    private String columnComment;                       //列的注释

    private boolean primaryKey = false;               //是否为主键

    private boolean autoIncrement = false;            //是否自增

    private boolean nullAble = false;                 //是否允许为空

    private boolean foreignKey = false;               //是否是外键

    private String propertyName;                        //生成的属性名字

    private String propertyGetMethodName;               //属性 get方法的名字

    private String propertySetMethodName;               //属性 set方法的名字

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        this.setPropertyName(StringUtils.formatField(this.columnName.toLowerCase()));
        this.setPropertyGetMethodName("get" + StringUtils.upperFirestChar(StringUtils.formatField(this.columnName.toLowerCase())));
        this.setPropertySetMethodName("set" + StringUtils.upperFirestChar(StringUtils.formatField(this.columnName.toLowerCase())));
    }

    public String getColumnType() {
        return StringUtils.getColumnType(Integer.parseInt(columnType));
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyGetMethodName() {
        return propertyGetMethodName;
    }

    public void setPropertyGetMethodName(String propertyGetMethodName) {
        this.propertyGetMethodName = propertyGetMethodName;
    }

    public String getPropertySetMethodName() {
        return propertySetMethodName;
    }

    public void setPropertySetMethodName(String propertySetMethodName) {
        this.propertySetMethodName = propertySetMethodName;
    }
}
