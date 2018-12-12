package basicBI;

import client.ChildTaskCleaner;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

abstract public class BITask extends JFrame implements WindowListener {
    static public final int LIVE_FOREVER=-1;
    protected ChildTaskCleaner cleaner;

    public BITask(String name, ChildTaskCleaner cleaner){
        super("BeautyInlet - "+name);
        this.cleaner=cleaner;
        addWindowListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void ending(){
        cleaner.deleteMe(this);
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
