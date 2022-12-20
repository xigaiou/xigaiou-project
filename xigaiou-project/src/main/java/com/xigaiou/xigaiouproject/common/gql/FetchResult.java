package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;

public interface FetchResult extends ActionResult{
    FetchLocalContext getFetchContext();

    Object getFineData();

    <T> PageRows<T> getAsPageRows(String s);
}
