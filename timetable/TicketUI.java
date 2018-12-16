package timetable;

import basicBI.BIColor;
import basicBI.BIFont;
import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class TicketUI extends JPanel implements ActionListener,TicketUpdateMed {
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

    public TicketUI(@NotNull Ticket ticket, TicketDeleter deleter) {
        super();
        this.myTicket = ticket;
        editor=new TicketEditor(this,myTicket);
        this.deleter=deleter;

        initComponent();
    }
    protected void initComponent(){
        removeAll();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        setOpaque(true);
        setBackground(BIColor.Black);

        name=new JLabel(myTicket.name);
        name.setOpaque(true);
        name.setFont(BIFont.big);
        name.setBackground(BIColor.Black);
        name.setForeground(BIColor.White);
        add(name);

        taskKind=new JLabel(myTicket.taskKind.toString());
        taskKind.setOpaque(true);
        taskKind.setFont(BIFont.contents);
        taskKind.setBackground(BIColor.Black);
        taskKind.setForeground(BIColor.White);
        add(taskKind);

        time=new JLabel(
                Integer.toString(myTicket.time.get(Calendar.HOUR_OF_DAY))
        +":"
        +Integer.toString(myTicket.time.get(Calendar.MINUTE)));
        time.setOpaque(true);
        time.setFont(BIFont.contents);
        time.setBackground(BIColor.Black);
        time.setForeground(BIColor.White);
        add(time);

        done=new JLabel(myTicket.isDone?"本日は実行済み":"");
        done.setOpaque(true);
        done.setFont(BIFont.contents);
        done.setBackground(BIColor.Black);
        done.setForeground(BIColor.White);
        add(done);

        add(new JSeparator(JSeparator.HORIZONTAL));

        StringBuilder enablesTextBuilder=new StringBuilder();
        for(int i=0;i<myTicket.enable.length;i++){
            enablesTextBuilder.append(myTicket.enable[i] ?Ticket.enableFormat[i]:"　");
        }
        enables=new JLabel(enablesTextBuilder.toString());
        enables.setOpaque(true);
        enables.setFont(BIFont.contents);
        enables.setBackground(BIColor.Black);
        enables.setForeground(BIColor.White);
        add(enables);

        edit=new JButton("編集");
        edit.setContentAreaFilled(false);
        edit.setFont(BIFont.contents);
        edit.setBackground(BIColor.Black);
        edit.setForeground(BIColor.White);
        edit.setActionCommand("EDIT");
        edit.addActionListener(this);
        add(edit);

        delete=new JButton("削除");
        delete.setContentAreaFilled(false);
        delete.setFont(BIFont.contents);
        delete.setBackground(BIColor.Black);
        delete.setForeground(BIColor.Accent);
        delete.setFont(BIFont.contents);
        delete.setActionCommand("DELETE");
        delete.addActionListener(this);
        add(delete);
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
        if(e.getActionCommand().equals(delete.getActionCommand())){
            deleter.delete(myTicket);
        }
    }

    @Override
    public void updateTicket(Ticket ticket) {
        this.myTicket=ticket;
        dataChanged();
    }
}
