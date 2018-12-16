package timetable;

import basicBI.BIColor;
import basicBI.BIFont;
import client.Boss;
import datamanager.DataBoxKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableComponent extends JPanel implements ActionListener, TicketDeleter {
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
        data= (TimeTableData) Boss.dataBox.get(DataBoxKey.TIMETABLE);

        setPreferredSize(new Dimension(500,addButtonHeight+ ticketUIHeight *data.ticketBox.size()));
        setOpaque(true);
        setBackground(BIColor.White);

        LayoutManager layout=new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(layout);

        for(Ticket i:data.ticketBox) {
            TicketUI ticketUI = new TicketUI(i,this);
            ticketUI.setPreferredSize(new Dimension(500, ticketUIHeight));
            add(ticketUI);
        }
        addButton=new JButton("追加");
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);
        addButton.setBackground(BIColor.Black);
        addButton.setForeground(BIColor.White);
        addButton.setFont(BIFont.big);
        addButton.setActionCommand("ADD");
        addButton.setPreferredSize(new Dimension(500,addButtonHeight));
        addButton.addActionListener(this);
        add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(addButton.getActionCommand())){
            data.ticketBox.add(new Ticket());
            dataChanged();
        }
    }

    public void dataChanged(){
        initComponent();
        updateUI();
    }

    @Override
    public void delete(Ticket ticket) {
        data.ticketBox.remove(ticket);
        dataChanged();
    }
}
