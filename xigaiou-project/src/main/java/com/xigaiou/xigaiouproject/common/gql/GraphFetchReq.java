package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;

import java.util.Map;

public class GraphFetchReq extends BaseFetchReq<String>{
    public GraphFetchReq(String gql, Map<String, Object> map){
        super(gql, map);
    }
    public GraphFetchReq(String gql){
        super(gql);
    }

    public EngineTypeEnum getEngineType(){
        return EngineTypeEnum.GRAPHQL;
    }

    @Override
    public PageRows<String> getPage() {
        return null;
    }
}
