package com.cshuig.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cshuig on 15/4/26.
 */
public class InputInfo {

    private String dbDriver;
    private String url;
    private String userName;
    private String password;
    private String schema;

    private String packageName;
    private String ourDir;
    private String author;

    private List<String> selectTableList = new ArrayList<>();

    public InputInfo() {

    }
    public InputInfo(String dbDriver, String url, String userName, String password) {
        this.dbDriver = dbDriver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public InputInfo(String dbDriver, String url, String userName, String password, String schema) {
        this.dbDriver = dbDriver;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.schema = schema;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getOurDir() {
        return ourDir.replace(".", "/");
    }

    public void setOurDir(String ourDir) {
        this.ourDir = ourDir;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getSelectTableList() {
        return selectTableList;
    }

    public void setSelectTableList(List<String> selectTableList) {
        this.selectTableList = selectTableList;
    }
}
