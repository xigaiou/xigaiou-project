package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gql.Action;
import com.xigaiou.xigaiouproject.common.gql.ActionReq;
import com.xigaiou.xigaiouproject.common.gql.ActionResult;
import com.xigaiou.xigaiouproject.common.gql.Engine;
import com.xigaiou.xigaiouproject.common.gql.EngineTypeEnum;
import com.xigaiou.xigaiouproject.common.gql.FetchReq;
import com.xigaiou.xigaiouproject.common.gql.FetchResult;
import com.xigaiou.xigaiouproject.common.gql.Fetcher;

import java.util.Map;

public interface GqlService {
    void addFetchers(Fetcher[] fetchers);

    void addFetchers(Iterable<Fetcher> fetchers);

    void addActions(Iterable<Action> Actions);

    void addActions(Action[] actions);

    <T> Fetcher<T> getFetcher(String name, boolean required);

    <T> Action<T> getAction(String name, boolean required);

    Fetcher[] getFetchers();

    Engine getEngine(EngineTypeEnum engineTypeEnum);

    FetchResult executeFetch(FetchReq fetchReq);

    @Deprecated
    default FetchResult execute(FetchReq fetchReq){
        return executeFetch(fetchReq);
    }

    ActionResult executeAction(ActionReq actionReq);

    ActionResult executeGql(String gql);

    ActionResult executeGql(String gql, Map<String, Object> map);
}
