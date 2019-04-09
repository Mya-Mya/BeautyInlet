package timetable;

import basicBI.BIColor;
import basicBI.BIUIFactory;
import client.Boss;
import datamanager.DataBoxKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTableComponent extends JPanel implements ActionListener, TicketDeleter {
    TimeTableData data;
    JButton addButton;

    public TimeTableComponent() {
        super();
        initComponent();
    }

    private synchronized void initComponent() {
        removeAll();
        data = (TimeTableData) Boss.dataBox.get(DataBoxKey.TIMETABLE);

        setBackground(BIColor.base);

        BoxLayout layout=new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(2);
        Dimension tiketUiSize=new Dimension(Boss.BOSS_WIDTH-100,80);
        for (Ticket i : data.ticketBox) {
            TicketUI ticketUI = new TicketUI(i, this);
            ticketUI.setPreferredSize(tiketUiSize);
            add(ticketUI);
        }

        addButton = BIUIFactory.createButton("＋", "ADD", this);
        addButton.setPreferredSize(new Dimension(Boss.BOSS_WIDTH, 40));
        add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(addButton.getActionCommand())) {
            data.ticketBox.add(new Ticket());
            dataChanged();
        }
    }

    public void dataChanged() {
        initComponent();
        updateUI();
    }

    @Override
    public void delete(Ticket ticket) {
        data.ticketBox.remove(ticket);
        dataChanged();
    }
}
