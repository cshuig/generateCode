package com.cshuig.utils;

import java.sql.Types;

/**
 * Created by cshuig on 15/4/26.
 */
public class StringUtils {

    public static String formatField(String field) {
        String[] columns = field.split("_");
        String tempField = columns[0];
        for (int i = 1; i < columns.length; i++) {
            tempField = tempField + upperFirestChar(columns[i]);
        }
        return tempField;
    }

    //首字母大写
    public static String upperFirestChar(String src) {
        if (src == null) return "";
        return src.substring(0, 1).toUpperCase().concat(src.substring(1));
    }

    public static String getColumnType(int databaseType) {
        String colType = "";

        switch (databaseType) {
            case Types.DECIMAL:
                colType = "BigDecimal";
                break;
            case Types.REAL:
                colType = "Float";
                break;

            case Types.INTEGER:
            case Types.BIGINT:
            case Types.TINYINT:
                colType = "Integer";
                break;

            case Types.FLOAT :
                colType = "Float";
                break;

            case Types.DOUBLE :
                colType = "Double";
                break;

            case Types.VARCHAR:
            case Types.CHAR:
            case Types.LONGVARBINARY:
            case Types.LONGVARCHAR:
                colType = "String";
                break;

            case Types.DATE:
            case Types.TIMESTAMP:
                colType="Date";
                break;

            default:
                colType = "String";
                break;
        }
        return colType;
    }
}
