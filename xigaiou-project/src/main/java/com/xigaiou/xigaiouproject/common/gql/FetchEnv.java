package com.xigaiou.xigaiouproject.common.gql;

public interface FetchEnv extends ActionEnv{
    default FetchLocalContext getFetchContext(){
        return (FetchLocalContext)this.getContext();
    }

    default <T extends FetchReq> T getFetchReq(){
        return this.getFetchContext().getFetchReq();
    }

    String getFl();
}
