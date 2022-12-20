package com.xigaiou.xigaiouproject.common.gql;

import lombok.Getter;

import java.util.HashMap;

public class DefaultActionContext implements ActionContext{
    private HashMap<String, Object> map;

    @Getter
    private transient ActionReq actionReq;

    @Getter
    private final transient Action action;

    public DefaultActionContext(ActionReq actionReq, Action action){
        this.actionReq = actionReq;
        this.action = action;
        this.map = new HashMap<>();
    }

    public boolean containsKey(String key){
        return map.containsKey(key);
    }

    public Object get(String key){
        return map.get(key);
    }

    public Object put(String key, Object value){
        return map.put(key, value);
    }
}
