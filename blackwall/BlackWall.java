package blackwall;

import basicBI.BIColor;
import basicBI.BITask;
import basicBI.BIUIFactory;
import client.Boss;
import client.ChildTaskCleaner;
import datamanager.DataBoxKey;
import timetable.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BlackWall extends BITask implements ActionListener {
    private JButton closeBut;
    private javax.swing.Timer timer;

    public BlackWall(ChildTaskCleaner cleaner, Ticket ticket) {
        super(cleaner, ticket);
        setDefaultLookAndFeelDecorated(false);
        BlackWallData data = (BlackWallData) Boss.dataBox.get(DataBoxKey.BLACKWALL);
        setPreferredSize(new Dimension(data.Width, data.Height));
        getContentPane().setBackground(BIColor.base);
        setBackground(BIColor.base);
        setResizable(true);
        setAlwaysOnTop(true);

        timer = new Timer(1000 * 60 * 50, this);
        timer.setActionCommand("TIMER");
        timer.start();

        closeBut = BIUIFactory.createButton("閉じる", "CLOSE", this);
        getContentPane().add(closeBut, BorderLayout.SOUTH);

        setVisible(true);
        pack();
    }

    @Override
    public void ending() {
        BlackWallData data = (BlackWallData) Boss.dataBox.get(DataBoxKey.BLACKWALL);
        data.Width = getWidth(); data.Height = getHeight();
        super.ending();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(closeBut.getActionCommand())) ending();
        if (e.getActionCommand().equals(timer.getActionCommand())) ending();
    }
}
