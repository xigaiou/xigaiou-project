package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;

public interface FetchReq<T> extends ActionReq<T>{
    PageRows<T> getPage();//替换原来的Page为苞米豆的Page（X），由于构造函数不满足，故替换为自己的分页类

    String getOrderBy();
}
