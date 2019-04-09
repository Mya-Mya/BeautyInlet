package timetable;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class TimeTableData implements Serializable {
    public List<Ticket> ticketBox = new LinkedList<>();
}
