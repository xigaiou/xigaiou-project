package com.xigaiou.xigaiouproject.common.gql;

public interface Fetcher<T> extends Action<T> {
    String getGraphFieldName();

    boolean isFetchUniqueRow();
}
