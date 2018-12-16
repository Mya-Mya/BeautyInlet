package client;

import basicBI.BIColor;
import basicBI.BIFont;
import timetable.TaskKind;
import timetable.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JFrame implements ActionListener {
    RunnerMed toSend;

    JPanel mainPanel;
    JTextField param1;
    JTextField param2;
    public Runner(RunnerMed toSend){
        super();
        this.toSend=toSend;
        setPreferredSize(new Dimension(600,600));

         mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(TaskKind.values().length+2,1));
        mainPanel.setBackground(BIColor.Black);
        for(TaskKind t:TaskKind.values()){
            JButton button=new JButton(t.toString());
            button.setContentAreaFilled(false);
            button.setBackground(BIColor.Black);
            button.setForeground(BIColor.White);
            button.setFont(BIFont.enormous);
            button.addActionListener(this);
            button.setActionCommand(t.toString());
            mainPanel.add(button);
        }
        param1=new JTextField("第一パラメーター");
        param1.setFont(BIFont.big);
        mainPanel.add(param1);
        param2=new JTextField("第二パラメーター");
        param2.setFont(BIFont.big);
        param1.setFont(BIFont.big);
        mainPanel.add(param2);

        add(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(false);
    }

    public void showUP(){
        setVisible(true);
        mainPanel.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionC= e.getActionCommand();
        TaskKind taskKind= TaskKind.search(actionC);
        if (taskKind==null) {
            return;
        }
         Ticket ticket=new Ticket();
        ticket.isDone=false;
        ticket.name="Runnerより起動されました : "+actionC;
        ticket.taskKind=taskKind;
        ticket.param1=param1.getText();
        ticket.param2=param2.getText();
        toSend.runnerLaunch(ticket);
    }
}
