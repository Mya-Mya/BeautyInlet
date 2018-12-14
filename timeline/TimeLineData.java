package timeline;

import java.io.Serializable;

public class TimeLineData implements Serializable {
    static final public int COLUMNUM_TASKKIND =1;
    static final public int COLUMNUM_TIME =4;

    static final public String[] colums={"名称","タスク種別","第一引数","第二引数","時刻","日","月","火","水","木","金","土"} ;
    static public Object[] getNewRow(){
        Object[] value=new Object[]{"書いてね","選んでね","書いてね","書いてね","選んでね",new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true)};
        return value;
    }
    public Object[][] value={
            getNewRow(),
            getNewRow(),
    };
    public TimeLineData(){}
}
