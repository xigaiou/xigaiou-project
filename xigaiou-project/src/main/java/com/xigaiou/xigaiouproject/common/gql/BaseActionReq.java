package com.xigaiou.xigaiouproject.common.gql;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public abstract class BaseActionReq<T> implements ActionReq<T>{
    private Map<String, Object> map;
    private T input;

    public BaseActionReq(T input, Map<String, Object> map){
        this.input = input;
        this.map = map;
    }

    public boolean existsKey(String key){
        return map.containsKey(key);
    }

    public <T> T getKey(String key){
        return (T) map.get(key);
    }

    public <T> T getKey(String key, T defaultVal){
        return (T) map.getOrDefault(key, defaultVal);
    }

    public void setMap(String key, Object value){
        map.put(key, value);
    }
}
