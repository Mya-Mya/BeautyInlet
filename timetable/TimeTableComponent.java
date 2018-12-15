package timetable;

import basicBI.BIColor;
import client.Boss;
import datamanager.DataKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableComponent extends JPanel implements ActionListener{
    TimeTableData data;
    final int ticketUIHeight =40;
    JButton addButton;
    int addButtonHeight=20;

    public TimeTableComponent(){
        super();
        initComponent();
    }

    private synchronized void initComponent(){
        removeAll();
        data= (TimeTableData) Boss.datas.get(DataKey.TIMETABLE);
        setPreferredSize(new Dimension(500,addButtonHeight+ ticketUIHeight *data.ticketBox.size()));
        setBackground(BIColor.Black);

        LayoutManager layout=new GridLayout(0,1,15,5);
        setLayout(layout);

        for(Ticket i:data.ticketBox) {
            TicketUI ticketUI = new TicketUI(i);
            ticketUI.setPreferredSize(new Dimension(500, ticketUIHeight));
            add(ticketUI);
        }
        addButton=new JButton("追加");
        addButton.setActionCommand("ADD");
        addButton.setPreferredSize(new Dimension(500,addButtonHeight));
        addButton.addActionListener(this);
        add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(addButton.getActionCommand())){
            data.ticketBox.add(new Ticket());
            dataUpdated();
        }
    }

    private void dataUpdated(){
        initComponent();
        updateUI();
    }
}
