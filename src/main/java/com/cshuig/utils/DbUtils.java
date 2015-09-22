package com.cshuig.utils;

import com.cshuig.model.Column;
import com.cshuig.model.Database;
import com.cshuig.model.Table;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cshuig on 15/4/26.
 */
public class DbUtils {

    public static Connection initConnection(Database database) {
        Connection connection = null;
        try {
            Class.forName(database.getInputInfo().getDbDriver());
            connection = DriverManager.getConnection(database.getInputInfo().getUrl(), database.getInputInfo().getUserName(), database.getInputInfo().getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void getDBInfo(Database database) throws SQLException {
        Connection connection = initConnection(database);
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        database.setDbType(databaseMetaData.getDatabaseProductName());
        database.setDbVersion(databaseMetaData.getDatabaseProductVersion());

        ResultSet resultSet = databaseMetaData.getTables(connection.getCatalog(), database.getInputInfo().getSchema(), null, new String[] {"TABLE"});
        while (resultSet.next()) {
            Table table = new Table();
            table.setTableName(resultSet.getString("TABLE_NAME"));
            table.setTableComment(resultSet.getString("REMARKS"));
            ResultSet resultSetForColumns = databaseMetaData.getColumns(null, null, table.getTableName(), null);
            while (resultSetForColumns.next()) {
                Column column = new Column();

                column.setColumnName(resultSetForColumns.getString("COLUMN_NAME"));
                column.setColumnType(resultSetForColumns.getString("DATA_TYPE"));
                column.setColumnComment(resultSetForColumns.getString("REMARKS"));
                column.setAutoIncrement("YES".equals(resultSetForColumns.getString("IS_AUTOINCREMENT")));
                column.setNullAble("YES".equals(resultSetForColumns.getString("IS_AUTOINCREMENT")));
                table.getColumnList().add(column);
            }

            // 设置主键列
            ResultSet rsPrimary = databaseMetaData.getPrimaryKeys(null, null, table.getTableName());
            while (rsPrimary.next()) {
                if (rsPrimary.getString("COLUMN_NAME") != null) {

                    for (int i = 0; i < table.getColumnList().size(); i++) {
                        Column column = table.getColumnList().get(i);
                        if (column.getColumnName().equals(rsPrimary.getString("COLUMN_NAME"))) {
                            column.setPrimaryKey(true);
                        }
                    }

                }
            }

            // 设置外键列
            ResultSet rsFPrimary = databaseMetaData.getImportedKeys(null, null, table.getTableName());
            while (rsFPrimary.next()) {

                for (int i = 0; i < table.getColumnList().size(); i++) {
                    Column column = table.getColumnList().get(i);
                    if (column.getColumnName().equals(rsFPrimary.getString("FKCOLUMN_NAME"))) {
                        column.setForeignKey(true);
                    }
                }
            }
            database.getTableList().add(table);
        }

    }
}
