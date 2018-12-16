package client;

import basicBI.BIColor;
import basicBI.BIFont;
import blackwall.BlackWall;
import blackwall.BlackWallData;
import basicBI.BITask;
import datamanager.DataBoxKey;
import datamanager.DataBox;
import datamanager.Serializer;
import timetable.Ticket;
import timetable.TimeTableComponent;
import timetable.TimeTableData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class Boss extends JFrame implements ChildTaskCleaner , WindowListener, ActionListener, RunnerMed {
    Serializer serializer;
    static public DataBox dataBox;
    Timer timer;
    TimeTableComponent timeTableComponent;
    JButton showRunner;
    Runner runner;
    private Collection<BITask> childBITasks =new ArrayList<BITask>();

    public Boss(String version){
        super("BeautyInlet -"+version);
        //ボスフレームの仮初期化
        setPreferredSize(new Dimension(500,500));
        JLabel bland=new JLabel("BeautyInlet "+version);
        bland.setFont(BIFont.enormous);
        bland.setHorizontalAlignment(JTextField.CENTER);
        bland.setForeground(BIColor.Assort);
        add( bland,BorderLayout.NORTH);
        setVisible(true);
        pack();

        //データの読み込み
        serializer =new Serializer(version+".bi");
        dataBox = (DataBox) serializer.load();
        if(dataBox ==null) {
            dataBox =new DataBox();
            dataBox.set(DataBoxKey.BOSS,new BossData());
            dataBox.set(DataBoxKey.TIMETABLE,new TimeTableData());
            dataBox.set(DataBoxKey.BLACKWALL,new BlackWallData());
        }
        //ウィンドウの構築
        BossData bossData= (BossData) dataBox.get(DataBoxKey.BOSS);
        setLocation(bossData.X,bossData.Y);
        setPreferredSize(new Dimension(bossData.Width, bossData.Height));

        //タイムテーブルデータの初期設定
        TimeTableData timeTable= (TimeTableData) dataBox.get(DataBoxKey.TIMETABLE);
        for(Ticket t:timeTable.ticketBox){
            t.isDone=false;
        }

        //タイムテーブルコンポーネントの起動
        timeTableComponent=new TimeTableComponent();
       add(new JScrollPane(
               timeTableComponent,
               ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
       ),BorderLayout.CENTER);

       //ランナーの起動
         runner=new Runner(this);
         showRunner =new JButton("ランナー表示");
        showRunner.setActionCommand("SHOWRUNNER");
        showRunner.setForeground(BIColor.Accent);
        showRunner.addActionListener(this);
        add(showRunner, BorderLayout.SOUTH);

       //タイマー起動
        timer =new Timer(1000*5,this);
        timer.setActionCommand("TIMER");
        timer.start();

        //ボスフレームの最終初期化
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(this);
        pack();
    }

    protected void ending() {
        while (childBITasks.size()!=0) {
            childBITasks.iterator().next().ending();
        }

        BossData bossData= (BossData) dataBox.get(DataBoxKey.BOSS);
        bossData.X=getX();
        bossData.Y=getY();
        bossData.Height=getHeight();
        bossData.Width=getWidth();
        serializer.save(dataBox);
        System.exit(0);
    }

    protected void checkTimeTable(){
        //タイムテーブルを引き出す
        TimeTableData timeTable= (TimeTableData) dataBox.get(DataBoxKey.TIMETABLE);
        //時刻と曜日を取得
        Calendar now=Calendar.getInstance();
        int h=now.get(Calendar.HOUR_OF_DAY);
        int m=now.get(Calendar.MINUTE);
        int week=now.get(Calendar.DAY_OF_WEEK)-1;//Ticketの週書式に合わせるため
        //時刻と曜日を比べる
        for(Ticket t: timeTable.ticketBox){
            if(t.time.get(Calendar.HOUR_OF_DAY)==h&&
            t.time.get(Calendar.MINUTE)==m&&
            t.enable[week]&&
            !t.isDone){
                launchTask(t);
            }
        }
    }

    protected void launchTask(Ticket ticket){
        ticket.isDone=true;
        BITask newTask=null;
        switch (ticket.taskKind){
            case BLACKWALL:
                newTask=new BlackWall(this,ticket);
                break;
            case EXIT:
                ending();
                break;
            case DISPLAYWEB:
                break;
        }
        timeTableComponent.dataChanged();

    }
    @Override
    public void runnerLaunch(Ticket ticket) {
        launchTask(ticket);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(timer.getActionCommand())){
            checkTimeTable();
        }
        if(e.getActionCommand().equals(showRunner.getActionCommand())){
            runner.showUP();
        }
    }

    @Override
    public void deleteBITask(BITask you) {
        childBITasks.remove(you);
    }

    @Override
    public void windowOpened(WindowEvent e) { }

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
