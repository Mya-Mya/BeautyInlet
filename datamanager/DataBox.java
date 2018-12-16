package datamanager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataBox implements Serializable {
    protected Map<DataBoxKey,Object> values=new HashMap<DataBoxKey,Object>();
    public void set(DataBoxKey dataBoxKey, Object object){
        values.put(dataBoxKey,object);
    }
    public Object get(DataBoxKey dataBoxKey){
        return values.get(dataBoxKey);
    }
}
