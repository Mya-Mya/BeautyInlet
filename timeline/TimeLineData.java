package timeline;

import java.io.Serializable;

public class TimeLineData implements Serializable {
    static final public int COLUMNUM_BITASKKIND=1;
    static final public int COLUMNUM_TIME =4;

    final public String[] colums={"名称","BIタスク種別","第一引数","第二引数","時刻","日","月","火","水","木","金","土"} ;
    final public Object[] newRow={"書いてね","選んでね","書いてね","書いてね","選んでね",new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true),new Boolean(true)};

    public Object[][] value={
            newRow
    };
    public TimeLineData(){}
}
