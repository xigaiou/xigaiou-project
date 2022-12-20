package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class DefaultGeneralFetchReq<T> extends BaseFetchReq<T> implements GeneralFetchReq<T>{
    private String ns;
    private String name;
    private String fl;
    private boolean fetchUniqueRow;

    public DefaultGeneralFetchReq(String name, T input){
        this("", name, input, new HashMap());
    }

    public DefaultGeneralFetchReq(String ns, String name, T input){
        this(ns, name, input, new HashMap());
    }

    public DefaultGeneralFetchReq(String ns, String name, T input, Map<String, Object> map){
        super(input,map);
        this.ns = ns;
        this.name = name;
    }

    public boolean isFetchUniqueRow(){
        return this.fetchUniqueRow;
    }

    @Override
    public PageRows<T> getPage() {
        return null;
    }
}
