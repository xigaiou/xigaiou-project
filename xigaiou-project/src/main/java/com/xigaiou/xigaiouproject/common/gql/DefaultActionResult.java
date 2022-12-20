package com.xigaiou.xigaiouproject.common.gql;

import java.util.Map;

public class DefaultActionResult implements ActionResult{
    private final ActionContext context;
    private final Map<String, Object> dataMap;

    public DefaultActionResult(ActionContext context, Map<String, Object> dataMap){
        this.context = context;
        this.dataMap = dataMap;
    }
    public Map<String, Object> getDataMap(){
        return this.dataMap;
    }
    public <T> T getFirstData(){
        return (T) this.getDataMap().values().iterator().next();
    }
    public ActionContext getContext(){return this.context;}
}
