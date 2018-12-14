package timeline;

import basicBI.BIColor;
import basicBI.BIFont;
import client.Boss;
import datamanager.DataKey;
import timeline.TaskKindEditor;
import timeline.TimeEditor;
import timeline.TimeLineData;
import timeline.TimeLineModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TimeLineComponent extends JTable {
    TimeLineData data;
    DefaultTableModel model;
    public TimeLineComponent(){
        super();
        data= (TimeLineData) Boss.datas.get(DataKey.TIMELINE);
        model=new TimeLineModel(data.value,TimeLineData.colums);
        setModel(model);
        getColumnModel().getColumn(TimeLineData.COLUMNUM_TASKKIND).setCellEditor(new TaskKindEditor());
        getColumnModel().getColumn(TimeLineData.COLUMNUM_TIME).setCellEditor(new TimeEditor());
        setRowHeight(30);
        setBackground(BIColor.Base);
        setForeground(BIColor.Black);
        setFont(BIFont.Contents);
    }
    public void addRow(){
        model.addRow(TimeLineData.getNewRow());
    }
}
