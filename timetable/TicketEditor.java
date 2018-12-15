package timetable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class TicketEditor extends JFrame implements ActionListener {
    Ticket ticket;
    TicketUpdateMed updater;
    TicketEditor editor;

    JTextField name;
    JComboBox taskKind;
    JTextField h;
    JTextField m;
    JTextField param1;
    JTextField param2;
    JCheckBox done;
    JButton update;

    JPanel p;

    public TicketEditor(TicketUpdateMed updater, Ticket ticket){
        super();
        this.updater=updater;
        this.ticket=ticket;
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponent();
        pack();
        setVisible(false);
    }
    protected void initComponent(){
        removeAll();

        p=new JPanel();
        p.setLayout(new GridLayout(10,2));

        JLabel lname=new JLabel("名前");
        p.add(lname);
        name=new JTextField(ticket.name);
        p.add(name);

        JLabel ltaskKing=new JLabel("種類");
        p.add(ltaskKing);

        Vector items=new Vector();
        for(TaskKind i: TaskKind.values()){
            items.add(i.toString());
        }
        taskKind=new JComboBox(items);
        taskKind.setSelectedItem(ticket.taskKind.toString());
        p.add(taskKind);

        JLabel lh=new JLabel("時");
        p.add(lh);
        h=new JTextField(ticket.time.get(Calendar.HOUR_OF_DAY));
        p.add(h);

        JLabel lm=new JLabel("分");
        p.add(lm);
        m=new JTextField(ticket.time.get(Calendar.MINUTE));
        p.add(m);

        JLabel lparam1=new JLabel("パラメーター1");
        p.add(lparam1);
        param1=new JTextField(ticket.param1);
        p. add(param1);

        JLabel lparam2=new JLabel("パラメーター2");
        p.add(lparam2);
        param2=new JTextField(ticket.param2);
        p.add(param2);

        //done未実装

        update=new JButton("完了");
        update.addActionListener(this);
        p.add(update);

        add(p);
    }

    public void showUp(){
        setVisible(true);
        p.updateUI();
    }
    public void setTicket(Ticket ticket){
        this.ticket=ticket;
        //initComponent();
        p.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticket.name=name.getText();
        ticket.taskKind=TaskKind.search((String) taskKind.getSelectedItem());
        ticket.time.set(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE),
                Integer.parseInt(h.getText()),
                Integer.parseInt(m.getText())
        );
        ticket.param1=param1.getText();
        ticket.param2=param2.getText();
        updater.updateTicket(ticket);
        setVisible(false);
    }
}
