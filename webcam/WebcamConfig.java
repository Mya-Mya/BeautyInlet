package webcam;

import basicBI.BITask;
import client.Boss;
import client.ChildTaskCleaner;
import com.github.sarxos.webcam.Webcam;
import datamanager.DataBoxKey;
import timetable.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WebcamConfig extends BITask implements ActionListener {
    WebcamConfigData configData;
    JTextField tSavingFolder;
    JButton bSavingFolder;
    JFileChooser savingFolderChooser;

    JComboBox camsCombo;

    JTextField widthPx;
    JTextField heightPx;

    JButton done;
    public WebcamConfig(ChildTaskCleaner cleaner, Ticket ticket) {
        super(cleaner, ticket);
        configData = (WebcamConfigData) Boss.dataBox.get(DataBoxKey.WEBCAM);
        setPreferredSize(new Dimension(500,500));

        setLayout(new GridLayout(12,4));

        JLabel lSavingFolder=new JLabel("保存場所");
        add(lSavingFolder);
        tSavingFolder=new JTextField(configData.savingFolder);
        add(tSavingFolder);
        bSavingFolder=new JButton("参照");
        bSavingFolder.setActionCommand("SAVINGFOLDER");
        bSavingFolder.addActionListener(this);
        add(bSavingFolder);

        add(new JSeparator());

        JLabel lUsingCam=new JLabel("使用するカメラ");
        add(lUsingCam);
        camsCombo=new JComboBox(Webcam.getWebcams().toArray());
        camsCombo.setSelectedItem(configData.usingCamName);
        add(camsCombo);

        add(new JSeparator());

        JLabel lPx=new JLabel("画像サイズ");
        widthPx=new JTextField(Integer.toString( configData.width));
        widthPx.setBorder(BorderFactory.createTitledBorder("幅"));
        add(widthPx);
        heightPx=new JTextField(Integer.toString(configData.height));
        heightPx.setBorder(BorderFactory.createTitledBorder("高さ"));
        add(heightPx);

        add(new JSeparator());

        done=new JButton("完了");
        done.addActionListener(this);
        add(done);

        pack();
        setVisible(true);

        savingFolderChooser=new JFileChooser();
        savingFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(bSavingFolder.getActionCommand())){
            int returnVal=savingFolderChooser.showOpenDialog(this);
            if(returnVal==JFileChooser.APPROVE_OPTION){
                tSavingFolder.setText(savingFolderChooser.getCurrentDirectory().toString());
            }
        }
        if(e.getActionCommand().equals(done.getActionCommand())){
            configData.savingFolder=tSavingFolder.getText();
            configData.usingCamName= camsCombo.getSelectedItem().toString();
            configData.width=Integer.parseInt(widthPx.getText());
            configData.height=Integer.parseInt(heightPx.getText());
            ending();
        }
    }
}
