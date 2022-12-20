package com.xigaiou.xigaiouproject.common.gql;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class MethodActionReq extends ParamMapActionReq implements MethodReq{
    @Setter
    @Getter
    private String method;
    @Getter
    private final Object body;

    public MethodActionReq(String ns, String name, String method, Object body, Map<String, Object> input){
        super(ns, name, input);
        this.body = body;
        this.method = method;
    }

    public MethodActionReq(String ns, String name, String method, Object body){
        this(ns, name, method, body, new HashMap<>());
    }

    public String getDataId(){
        return BaseAction.concatDataId(getNs(), getName(), new String[]{method});
    }

}
