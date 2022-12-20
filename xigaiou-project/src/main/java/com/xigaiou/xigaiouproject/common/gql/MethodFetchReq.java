package com.xigaiou.xigaiouproject.common.gql;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class MethodFetchReq extends ParamMapFetchReq implements MethodReq{
    private String method;
    private final Object body;

    public MethodFetchReq(String ns, String name, String method, Object body, Map<String, Object> input){
        super(ns, name, input);
        this.body = body;
        this.method = method;
    }

    public MethodFetchReq(String ns, String name, Object body, Map<String, Object> input){
        this(ns, name, (String)null, body, input);
    }

    public MethodFetchReq(String ns, String name, String method, Object body){
        this(ns, name, method, body, new HashMap<>());
    }

    public MethodFetchReq(String ns, String name, Object body){
        this(ns, name, (String)null, (Object)body);
    }

    public String getDataId(){
        return BaseAction.concatDataId(getNs(), getName(), new String[]{method});
    }
}
