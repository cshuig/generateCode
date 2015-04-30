package com.cshuig.model;

import javax.swing.table.DefaultTableModel;
import java.util.Objects;

/**
 * Created by cshuig on 15/4/26.
 */
public class TableCheckbox extends DefaultTableModel {

    private static final long serialVersionUID = 5558079615867475896L;

    public TableCheckbox(Objects[][] datas, String[] columns) {
        super(datas, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) return true;
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Boolean.class;
        }
        return Object.class;
    }
}
