package basicBI;

import client.ChildTaskCleaner;
import timetable.Ticket;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

abstract public class BITask extends JFrame implements WindowListener {
    protected ChildTaskCleaner cleaner;
    protected Ticket ticket;

    public BITask(ChildTaskCleaner cleaner, Ticket ticket){
        super("BeautyInlet - "+ticket.name);
        this.cleaner=cleaner;
        this.ticket=ticket;
        addWindowListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void ending(){
        cleaner.deleteBITask(this);
        setVisible(false);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        ending();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
