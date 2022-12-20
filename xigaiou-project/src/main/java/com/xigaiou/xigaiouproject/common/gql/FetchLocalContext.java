package com.xigaiou.xigaiouproject.common.gql;

public interface FetchLocalContext extends ActionContext{
    <T extends FetchReq> T getFetchReq();
}
