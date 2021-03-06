package com.elle.elle_gui.logic;

import java.lang.Object;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
//import org.apache.commons.lang3.StringUtils;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * EditableTableModel This class allows switching the table to editable and non
 * editable by overriding the isCellEditable method with a boolean to change it
 * on the fly.
 *
 * @author Carlos Igreja
 * @since June 10, 2015
 * @version 0.6.3
 */
public class EditableTableModel extends DefaultTableModel {

    private boolean cellEditable;
    private Vector columnClass;

    /**
     * CONSTRUCTOR EditableTableModel
     *
     * @param data
     * @param columnNames
     * @param isCellEditable
     */
    public EditableTableModel(Vector data, Vector columnNames, Vector colClass) {
        super(data, columnNames);
        cellEditable = false;
        columnClass = colClass;
    }

    /**
     * isCellEditable Makes table editable or non editable
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return cellEditable;
    }

    /**
     * isCellEditable
     *
     * @return
     */
    public boolean isCellEditable() {
        return cellEditable;
    }

    /**
     * setCellEditable
     *
     * @param cellEditable
     */
    public void setCellEditable(boolean cellEditable) {
        this.cellEditable = cellEditable;
    }
    
    
    public Vector<Object> getRowAt(int row) {
        Vector rowData = new Vector(columnClass.size());
        for (int i = 0; i < columnClass.size(); i++) {
            rowData.add(this.getValueAt(row, i));
        }
        return rowData;
    }
    
    public Vector<Object> getColumnAt(int col) {
        Vector colData = new Vector(this.getRowCount());
        for (int i = 0; i < this.getRowCount(); i++) {
            colData.add(this.getValueAt(i, col));
        }
        return colData;
    }

    /**
     * Override getColumnClass() in DefaultTableModel
     *
     * @param col
     * @return class
     */
    @Override
    public Class getColumnClass(int col) {
        // get class name of that column
        String columnClassName = (String) columnClass.get(col);

        int indexOfDotInClassName = columnClassName.indexOf(".", 5)+1;
        columnClassName = columnClassName.substring(indexOfDotInClassName).toLowerCase();
//        System.out.println(col + " " + columnClassName);
        
        switch(columnClassName){
            case "string":
                return String.class;
            case "integer": case "int": case "long":
                return Integer.class;
            case "date":
                return Date.class;
            case "bigdecimal":
                return BigDecimal.class;
            case "timestamp":
                return Timestamp.class;
            default:
                return Object.class;
                
        }

    }

}
