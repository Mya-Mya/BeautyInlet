package timetable;

import basicBI.BIFont;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class TicketUI extends JPanel implements ActionListener,TicketUpdateMed {
    Ticket myTicket;
    TicketEditor editor;
    JLabel name;
    JLabel taskKind;
    JLabel time;
    JLabel done;
    JButton edit;

    public TicketUI(@NotNull Ticket ticket) {
        super();
        this.myTicket = ticket;
        editor=new TicketEditor(this,myTicket);

        initComponent();
    }
    protected void initComponent(){
        removeAll();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        name=new JLabel(myTicket.name);
        name.setFont(BIFont.Contents);
        add(name);

        add(new JSeparator(JSeparator.VERTICAL));

        taskKind=new JLabel(myTicket.taskKind.toString());
        taskKind.setFont(BIFont.Contents);
        add(taskKind);

        time=new JLabel(
                Integer.toString(myTicket.time.get(Calendar.HOUR_OF_DAY))
        +":"
        +Integer.toString(myTicket.time.get(Calendar.MINUTE)));
        time.setFont(BIFont.Contents);
        add(time);

        done=new JLabel(myTicket.isDone?"本日は実行済み":"本日は未実行");
        done.setFont(BIFont.Contents);
        add(done);

        edit=new JButton("編集");
        edit.setActionCommand("EDIT");
        edit.setFont(BIFont.Contents);
        edit.addActionListener(this);
        add(edit);
    }

    protected void dataChanged(){
        editor.setTicket(myTicket);
        initComponent();
        updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(edit.getActionCommand())){
            editor.setTicket(myTicket);
            editor.showUp();
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        this.myTicket=ticket;
        dataChanged();
    }
}
