package timetable;

import basicBI.BIUIFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

public class TicketEditor extends JFrame implements ActionListener {
    Ticket ticket;
    TicketUpdateMed updater;
    TicketEditor editor;

    JTextField name;
    JComboBox taskKindCombo;
    JTextField h;
    JTextField m;
    JTextField param1;
    JTextField param2;
    JCheckBox enables[];
    JCheckBox done;
    JButton update;

    JPanel mainPanel;

    public TicketEditor(TicketUpdateMed updater, Ticket ticket) {
        super();
        this.updater = updater;
        this.ticket = ticket;
        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponent();
        pack();
        setVisible(false);
    }

    protected void initComponent() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 2));

        mainPanel.add(BIUIFactory.createLabel("名前"));
        name = BIUIFactory.createTextField(ticket.name);
        mainPanel.add(name);

        mainPanel.add(BIUIFactory.createLabel("種類"));

        Vector items = new Vector();
        for (TaskKind i : TaskKind.values()) {
            items.add(i.toString());
        }
        taskKindCombo = new JComboBox(items);
        taskKindCombo.setSelectedItem(ticket.taskKind.toString());
        mainPanel.add(taskKindCombo);

        mainPanel.add(BIUIFactory.createLabel("時"));
        h = BIUIFactory.createTextField(Integer.toString(ticket.time.get(Calendar.HOUR_OF_DAY)));
        mainPanel.add(h);

        mainPanel.add(BIUIFactory.createLabel("分"));
        m = BIUIFactory.createTextField(Integer.toString(ticket.time.get(Calendar.MINUTE)));
        mainPanel.add(m);

        mainPanel.add(BIUIFactory.createLabel("パラメーター1"));
        param1 = BIUIFactory.createTextField(ticket.param1);
        mainPanel.add(param1);

        mainPanel.add(BIUIFactory.createLabel("パラメーター2"));
        param2 = BIUIFactory.createTextField(ticket.param2);
        mainPanel.add(param2);

        //done未実装

        mainPanel.add(BIUIFactory.createLabel("実行される曜日"));
        JPanel enablesPanel = new JPanel();
        enablesPanel.setLayout(new GridLayout(1, ticket.enable.length));
        enables = new JCheckBox[ticket.enable.length];
        for (int i = 0; i < ticket.enable.length; i++) {
            enables[i] = new JCheckBox(Ticket.enableFormat[i], ticket.enable[i]);
            enablesPanel.add(enables[i]);
        }
        mainPanel.add(enablesPanel);

        update = BIUIFactory.createButton("完了", "EDITDONE", this);
        update.addActionListener(this);
        mainPanel.add(update);

        add(mainPanel);
    }

    public void showUp() {
        setVisible(true);
        mainPanel.updateUI();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        //initComponent();
        mainPanel.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticket.name = name.getText();
        ticket.taskKind = TaskKind.search((String) taskKindCombo.getSelectedItem());
        ticket.time.set(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE),
                Integer.parseInt(h.getText()),
                Integer.parseInt(m.getText())
        );
        ticket.param1 = param1.getText();
        ticket.param2 = param2.getText();
        for (int i = 0; i < ticket.enable.length; i++) {
            ticket.enable[i] = enables[i].isSelected();
        }
        updater.updateTicket(ticket);
        setVisible(false);
    }
}
