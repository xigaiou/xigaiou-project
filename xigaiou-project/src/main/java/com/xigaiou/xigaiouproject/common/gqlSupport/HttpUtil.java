package com.xigaiou.xigaiouproject.common.gqlSupport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class HttpUtil {
    private HttpUtil(){

    }

    public static Map<String, Object> convertRequestParamMap(Map<String, String[]> paramMap, boolean multiValues){
        HashMap<String, Object> newMap = new HashMap<>(paramMap.size());
        Iterator iterator = paramMap.entrySet().iterator();

        while(true){
            while(true){
                Map.Entry item;
                String[] value;
                do{
                    if(!iterator.hasNext()){
                        return newMap;
                    }
                    item = (Map.Entry)iterator.next();
                    value = (String[])item.getValue();
                } while(value.length < 1);
                if(value.length != 1 && multiValues){
                    newMap.put((String)item.getKey(), value);
                } else {
                    newMap.put((String)item.getKey(),value[0]);
                }
            }
        }
    }
}
