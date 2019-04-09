package webcam;

import basicBI.BITask;
import basicBI.BIUIFactory;
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
        setPreferredSize(new Dimension(500, 500));

        setLayout(new GridLayout(12, 4));

        add(BIUIFactory.createLabel("保存場所"));
        tSavingFolder = new JTextField(configData.savingFolder);
        add(tSavingFolder);

        bSavingFolder = BIUIFactory.createButton("参照", "CHOOSEFOLDER", this);
        add(bSavingFolder);

        add(new JSeparator());

        add(BIUIFactory.createLabel("使用するカメラ"));

        camsCombo = new JComboBox(Webcam.getWebcams().toArray());
        camsCombo.setSelectedItem(configData.usingCamName);
        add(camsCombo);

        add(new JSeparator());

        add(BIUIFactory.createLabel("画像サイズ"));
        widthPx = BIUIFactory.createTextField(Integer.toString(configData.width));
        widthPx.setBorder(BorderFactory.createTitledBorder("幅"));
        add(widthPx);
        heightPx = BIUIFactory.createTextField(Integer.toString(configData.height));
        heightPx.setBorder(BorderFactory.createTitledBorder("高さ"));
        add(heightPx);

        add(new JSeparator());

        done = BIUIFactory.createButton("完了", "DONE", this);
        add(done);

        pack();
        setVisible(true);

        savingFolderChooser = new JFileChooser();
        savingFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(bSavingFolder.getActionCommand())) {
            int returnVal = savingFolderChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                tSavingFolder.setText(savingFolderChooser.getCurrentDirectory().toString());
            }
        }
        if (e.getActionCommand().equals(done.getActionCommand())) {
            configData.savingFolder = tSavingFolder.getText();
            configData.usingCamName = camsCombo.getSelectedItem().toString();
            configData.width = Integer.parseInt(widthPx.getText());
            configData.height = Integer.parseInt(heightPx.getText());
            ending();
        }
    }
}
