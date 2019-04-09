package timetable;

import basicBI.BIColor;
import basicBI.BIFont;
import basicBI.BIUIFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class TicketUI extends JPanel implements ActionListener, TicketUpdateMed {
    Ticket myTicket;
    TicketEditor editor;
    TicketDeleter deleter;

    JLabel name;
    JLabel taskKind;
    JLabel time;
    JLabel enables;
    JLabel done;
    JButton edit;
    JButton delete;

    int PART_HEIGHT=30;

    public TicketUI(Ticket ticket, TicketDeleter deleter) {
        super();
        this.myTicket = ticket;
        editor = new TicketEditor(this, myTicket);
        this.deleter = deleter;

        initComponent();
    }

    protected void initComponent() {
        removeAll();
        setLayout(null);
        //時刻
        time=BIUIFactory.createLabel(Integer.toString(myTicket.time.get(Calendar.HOUR_OF_DAY))
                + ":"
                + Integer.toString(myTicket.time.get(Calendar.MINUTE))
        );
        time.setBounds(10,10,60,PART_HEIGHT);
        add(time);

        //タスク名
        name= BIUIFactory.createLabel(myTicket.name);
        name.setBounds(90,10,200,PART_HEIGHT);
        add(name);

        //タスク種別
        taskKind=BIUIFactory.createLabel(myTicket.taskKind.toString());
        taskKind.setBounds(30,50,150,PART_HEIGHT);
        add(taskKind);

        //実行済みかどうか
        done=BIUIFactory.createLabel(myTicket.isDone ? "済" : "");
        done.setFont(BIFont.contents);
        done.setBounds(300,10,60,PART_HEIGHT);
        add(done);

        //曜日
        StringBuilder enablesTextBuilder = new StringBuilder();
        for (int i = 0; i < myTicket.enable.length; i++) {
            enablesTextBuilder.append(myTicket.enable[i] ? Ticket.enableFormat[i] : "　");
        }
        enables=BIUIFactory.createLabel(enablesTextBuilder.toString());
        enables.setBounds(300,50,130,PART_HEIGHT);
        add(enables);

        //ボタン
        edit=BIUIFactory.createButton("編集","EDIT",this);
        edit.setBounds(440,10,100,PART_HEIGHT*2);
        add(edit);

        delete=BIUIFactory.createButton("削除","DELET",this);
        delete.setBounds(550,10,100,PART_HEIGHT*2);
        add(delete);

        this.setBorder(BorderFactory.createLineBorder(BIColor.accent,2));
    }

    protected void dataChanged() {
        editor.setTicket(myTicket);
        initComponent();
        updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(edit.getActionCommand())) {
            editor.setTicket(myTicket);
            editor.showUp();
        }
        if (e.getActionCommand().equals(delete.getActionCommand())) {
            deleter.delete(myTicket);
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        this.myTicket = ticket;
        dataChanged();
    }
}
