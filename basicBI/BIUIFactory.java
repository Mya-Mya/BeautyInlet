package basicBI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BIUIFactory {
    public static JLabel createLabel(String name){
        JLabel c=new JLabel();
        c.setText(name);
        c.setFont(BIFont.big);
        c.setHorizontalAlignment(SwingConstants.LEFT);
        c.setOpaque(false);
        c.setForeground(BIColor.assort);
        return c;
    }
    public static JTextField createTextField(String name){
        JTextField c=new JTextField();
        c.setText(name);
        c.setFont(BIFont.big);
        return c;
    }
    public static JButton createButton(String name, String acco, ActionListener aclis){
        JButton c=new JButton();
        c.setText(name);
        c.setBackground(BIColor.base);
        c.setForeground(BIColor.white);
        c.setFont(BIFont.big);
        c.setActionCommand(acco);
        c.addActionListener(aclis);
        c.setHorizontalAlignment(SwingConstants.CENTER);
        return c;
    }
    public static JPanel createPanel(){
        JPanel c=new JPanel();
        c.setBackground(BIColor.base);
        return c;
    }
}
