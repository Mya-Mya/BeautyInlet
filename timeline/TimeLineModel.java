package timeline;

import client.Boss;
import datamanager.DataKey;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TimeLineModel extends DefaultTableModel {
    public TimeLineModel(Object[][]data,String[]columnNames){
        super(data,columnNames);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){

    return getValueAt(0,columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }
}
