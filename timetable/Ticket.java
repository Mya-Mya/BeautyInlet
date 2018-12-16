package timetable;

import java.io.Serializable;
import java.util.Calendar;

public class Ticket implements Serializable {
    public Ticket(){
        time= Calendar.getInstance();
        time.set(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE),
                0,
                0
        );
        for(int i=0;i<enable.length;i++)enable[i]=true;
        this.isDone=false;
    }
    public String name="名前";
    public TaskKind taskKind=TaskKind.EXIT;
    public Calendar time=Calendar.getInstance();
    public String param1="各タスクの指定したパラメータ";
    public String param2="各タスクの指定したパラメータ";
    public static final String[] enableFormat=new String[]{"日","月","火","水","木","金","土"};
    public boolean[] enable=new boolean[7];
    public boolean isDone=false;//毎回起動時にfalseにしなければならない
}
