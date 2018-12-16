package blackwall;

import basicBI.BIColor;
import basicBI.BITask;
import client.Boss;
import client.ChildTaskCleaner;
import datamanager.DataBoxKey;
import timetable.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BlackWall extends BITask implements ActionListener{
    private JButton closeBut;
    private javax.swing.Timer timer;
    public BlackWall(ChildTaskCleaner cleaner, Ticket ticket) {
        super(cleaner,ticket);
        setDefaultLookAndFeelDecorated(false);
        BlackWallData data= (BlackWallData) Boss.dataBox.get(DataBoxKey.BLACKWALL);
        setPreferredSize(new Dimension(data.Width,data.Height));
        getContentPane().setBackground(BIColor.Black);
        setBackground(BIColor.Black);
        setResizable(true);
        setAlwaysOnTop(true);

        timer=new Timer(1000*60*50,this);
        timer.setActionCommand("TIMER");
        timer.start();

        closeBut=new JButton("閉じる");
            closeBut.setContentAreaFilled(false);
            closeBut.setForeground(BIColor.Accent);
            closeBut.addActionListener(this);
            closeBut.setVisible(true);
            closeBut.setActionCommand("closeBut");
            getContentPane().add(closeBut,BorderLayout.SOUTH);

        setVisible(true);
        pack();
    }

    @Override
    public void ending() {
        BlackWallData data= (BlackWallData) Boss.dataBox.get(DataBoxKey.BLACKWALL);
        data.Width=getWidth();
        data.Height=getHeight();
        super.ending();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(closeBut.getActionCommand()))ending();
        if(e.getActionCommand().equals(timer.getActionCommand()))ending();
    }
}
