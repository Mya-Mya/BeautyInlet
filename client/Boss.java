package client;

import blackwall.BlackWallData;
import basicBI.BITask;
import datamanager.DataKey;
import datamanager.Datas;
import datamanager.Seriarlizer;
import timeline.TimeLineComponent;
import timeline.TimeLineData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collection;

public class Boss extends JFrame implements ChildTaskCleaner , WindowListener {
    Seriarlizer seriarlizer;
    static public Datas datas;
    private Collection<BITask> childBITasks =new ArrayList<BITask>();

    public Boss(String version){
        super("BeautyInlet -"+version);
        //データの読み込み
        seriarlizer=new Seriarlizer(version+".bi");
        datas = (Datas) seriarlizer.load();
        if(datas ==null) {
            datas =new Datas();
            datas.set(DataKey.BOSS,new BossData());
            datas.set(DataKey.TIMELINE,new TimeLineData());
            datas.set(DataKey.BLACKWALL,new BlackWallData());
        }
        //ウィンドウの構築
        BossData bossData= (BossData) datas.get(DataKey.BOSS);
        setLocation(bossData.X,bossData.Y);
        setPreferredSize(new Dimension(bossData.Width, bossData.Height));

        //テーブルの構築
        TimeLineComponent timeLine=new TimeLineComponent();
        add(new JScrollPane(timeLine),BorderLayout.CENTER);

        JButton addTask=new JButton("タスク追加");
            addTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLine.addRow();
                }
            });
            add(addTask,BorderLayout.SOUTH);

        //初期化
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(this);
        setVisible(true);
        pack();
    }

    protected void ending() {
        while (childBITasks.size()!=0) {
            childBITasks.iterator().next().ending();
        }

        BossData bossData= (BossData) datas.get(DataKey.BOSS);
        bossData.X=getX();
        bossData.Y=getY();
        bossData.Height=getHeight();
        bossData.Width=getWidth();
        seriarlizer.save(datas);
        System.exit(0);
    }

    @Override
    public void deleteMe(BITask you) {
        childBITasks.remove(you);
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
