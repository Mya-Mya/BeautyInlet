package timeline;

import client.Boss;
import datamanager.DataKey;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TimeLineTableModel extends DefaultTableModel {
    TimeLineData data;
    public TimeLineTableModel(){
        super();
        data= (TimeLineData) Boss.datas.get(DataKey.TIMELINE);
    }
    @Override
    public void setValueAt(Object val,int rowIndex,int columnIndex){
        data.value[rowIndex][columnIndex]=val;
        fireTableCellUpdated(rowIndex,columnIndex);
    }
    @Override
    public Class<?> getColumnClass(int columnIndex){
        return data.value[0][columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }

    @Override
    public int getRowCount() {
        return data.value.length;
    }

    @Override
    public int getColumnCount() {
        return data.colums.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.value[rowIndex][columnIndex];
    }
    @Override
    public String getColumnName(int column){
        return data.colums[column];
    }
}
