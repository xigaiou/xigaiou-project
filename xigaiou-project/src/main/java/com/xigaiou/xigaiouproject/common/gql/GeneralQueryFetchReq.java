package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;
import lombok.Getter;

import java.util.Map;

public class GeneralQueryFetchReq extends ParamMapFetchReq{
    @Getter
    private final String sceneCode;

    public GeneralQueryFetchReq(String sceneCode, Map<String, Object> input){
        super("query", "employees-info-scene", input);
        this.sceneCode = sceneCode;
        if(null == getPage()){
            setPageRows(new PageRows<>(1,10));
        }
    }
}
