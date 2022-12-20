package com.xigaiou.xigaiouproject.common.gql;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@NoArgsConstructor
public class DefaultGeneralActionReq<T> extends BaseActionReq<T> implements GeneralActionReq<T>{
    @Getter
    private String ns;
    @Getter
    private String name;

    public DefaultGeneralActionReq(String ns, String name, T input){
        this(ns, name, input, new HashMap<>());
    }

    public DefaultGeneralActionReq(String ns, String name, T input, Map<String, Object> map){
        super(input, map);
        this.name = name;
        this.ns = ns;
    }
}
