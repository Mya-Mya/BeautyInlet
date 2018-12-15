package timetable;

import java.io.Serializable;
import java.util.Calendar;

public class Ticket implements Serializable {
    Ticket(){
        this.isDone=false;
    }
    public String name="名前";
    public TaskKind taskKind=TaskKind.EXIT;
    public Calendar time=Calendar.getInstance();
    public String param1="各タスクの指定したパラメータ";
    public String param2="各タスクの指定したパラメータ";
    public boolean isDone=false;//毎回起動時にfalseにしなければならない
}
