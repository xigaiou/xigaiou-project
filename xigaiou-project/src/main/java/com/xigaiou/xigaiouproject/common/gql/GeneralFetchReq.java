package com.xigaiou.xigaiouproject.common.gql;

public interface GeneralFetchReq<T> extends GeneralActionReq<T>, FetchReq<T> {
    String getFl();

    boolean isFetchUniqueRow();
}
