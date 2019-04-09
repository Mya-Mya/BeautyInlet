package client;

import basicBI.BIUIFactory;
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

    public Runner(RunnerMed toSend) {
        super();
        this.toSend = toSend;
        setPreferredSize(new Dimension(600, 600));

        mainPanel = BIUIFactory.createPanel();
        mainPanel.setLayout(new GridLayout(TaskKind.values().length + 2, 1));
        for (TaskKind t : TaskKind.values()) mainPanel.add(BIUIFactory.createButton(t.toString(), t.toString(), this));

        param1 = BIUIFactory.createTextField("第一パラメーター");
        mainPanel.add(param1);

        param2 = BIUIFactory.createTextField("第二パラメーター");
        mainPanel.add(param2);

        add(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(false);
    }

    public void showUP() {
        setVisible(true);
        mainPanel.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acco = e.getActionCommand();
        TaskKind taskKind = TaskKind.search(acco);
        if (taskKind == null) {
            return;
        }
        Ticket ticket = new Ticket();
        ticket.isDone = false;
        ticket.name = "Runnerより起動されました : " + acco;
        ticket.taskKind = taskKind;
        ticket.param1 = param1.getText();
        ticket.param2 = param2.getText();
        toSend.runnerLaunch(ticket);
    }
}
