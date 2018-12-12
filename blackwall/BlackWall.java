package blackwall;

import basicBI.BIColor;
import basicBI.BITask;
import client.Boss;
import client.ChildTaskCleaner;
import datamanager.DataKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackWall extends BITask implements ActionListener{
    private JButton closeBut;
    public BlackWall(String name, ChildTaskCleaner cleaner) {
        super(name, cleaner);
        setDefaultLookAndFeelDecorated(false);
        BlackWallData data= (BlackWallData) Boss.datas.get(DataKey.BLACKWALL);
        setPreferredSize(new Dimension(data.Width,data.Height));
        setBackground(BIColor.Base);
        setResizable(false);
        setAlwaysOnTop(true);

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
        super.ending();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(closeBut.getActionCommand()))ending();
    }
}
