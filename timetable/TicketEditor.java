package timetable;

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
    JComboBox taskKind;
    JTextField h;
    JTextField m;
    JTextField param1;
    JTextField param2;
    JCheckBox enables[];
    JCheckBox done;
    JButton update;

    JPanel mainPanel;

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

        mainPanel =new JPanel();
        mainPanel.setLayout(new GridLayout(10,2));

        JLabel lname=new JLabel("名前");
        mainPanel.add(lname);
        name=new JTextField(ticket.name);
        mainPanel.add(name);

        JLabel ltaskKing=new JLabel("種類");
        mainPanel.add(ltaskKing);

        Vector items=new Vector();
        for(TaskKind i: TaskKind.values()){
            items.add(i.toString());
        }
        taskKind=new JComboBox(items);
        taskKind.setSelectedItem(ticket.taskKind.toString());
        mainPanel.add(taskKind);

        JLabel lh=new JLabel("時");
        mainPanel.add(lh);
        h=new JTextField(Integer.toString( ticket.time.get(Calendar.HOUR_OF_DAY)));
        mainPanel.add(h);

        JLabel lm=new JLabel("分");
        mainPanel.add(lm);
        m=new JTextField(Integer.toString(ticket.time.get(Calendar.MINUTE)));
        mainPanel.add(m);

        JLabel lparam1=new JLabel("パラメーター1");
        mainPanel.add(lparam1);
        param1=new JTextField(ticket.param1);
        mainPanel. add(param1);

        JLabel lparam2=new JLabel("パラメーター2");
        mainPanel.add(lparam2);
        param2=new JTextField(ticket.param2);
        mainPanel.add(param2);

        //done未実装

        JLabel lenables=new JLabel("実行される曜日");
        mainPanel.add(lenables);
        JPanel enablesPanel=new JPanel();
            enablesPanel.setLayout(new GridLayout(1,ticket.enable.length));
            enables=new JCheckBox[ticket.enable.length];
            for(int i=0;i<ticket.enable.length;i++){
                enables[i]=new JCheckBox(Ticket.enableFormat[i],ticket.enable[i]);
                enablesPanel.add(enables[i]);
            }
        mainPanel.add(enablesPanel);

        update=new JButton("完了");
        update.addActionListener(this);
        mainPanel.add(update);

        add(mainPanel);
    }

    public void showUp(){
        setVisible(true);
        mainPanel.updateUI();
    }
    public void setTicket(Ticket ticket){
        this.ticket=ticket;
        //initComponent();
        mainPanel.updateUI();
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
        for(int i=0;i<ticket.enable.length;i++){
            ticket.enable[i]=enables[i].isSelected();
        }
        updater.updateTicket(ticket);
        setVisible(false);
    }
}
