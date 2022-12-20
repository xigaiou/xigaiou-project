package com.xigaiou.xigaiouproject.common.gql;

public final class DefaultFetchContext extends DefaultActionContext implements FetchLocalContext{
    public DefaultFetchContext(FetchReq fetchReq, Action action){
        super(fetchReq, action);
    }
    public FetchReq getFetchReq(){
        return (FetchReq)super.getActionReq();
    }
}
