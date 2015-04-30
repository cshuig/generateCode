package com.cshuig.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshuig on 15/4/26.
 */
public class Database {

    private InputInfo inputInfo;


    private String dbName;
    private String dbType;
    private String dbVersion;
    private List<Table> tableList = new ArrayList<>();

    public Database(InputInfo inputInfo) {
        this.inputInfo = inputInfo;
    }

    public Database() {
    }

    public InputInfo getInputInfo() {
        return inputInfo;
    }

    public void setInputInfo(InputInfo inputInfo) {
        this.inputInfo = inputInfo;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    public List<Table> getTableList() {
        if (tableList == null) {
            tableList = new ArrayList<>();
        }
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
}
