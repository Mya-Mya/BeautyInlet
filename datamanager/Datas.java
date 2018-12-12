package datamanager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Datas implements Serializable {
    protected Map<DataKey,Object> values=new HashMap<DataKey,Object>();
    public void set(DataKey dataKey,Object object){
        values.put(dataKey,object);
    }
    public Object get(DataKey dataKey){
        return values.get(dataKey);
    }
}
