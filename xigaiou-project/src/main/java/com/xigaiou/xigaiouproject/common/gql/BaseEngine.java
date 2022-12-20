package com.xigaiou.xigaiouproject.common.gql;

public abstract class BaseEngine implements Engine {
    protected GqlService gqlService;

    public BaseEngine(GqlService gqlService){
        this.gqlService = gqlService;
    }

    public abstract EngineTypeEnum getEngineType();

    public Fetcher getFetcher(String fetcherName){
        return gqlService.getFetcher(fetcherName, true);
    }

    public Action getAction(String actionName){
        return gqlService.getAction(actionName, true);
    }

    protected FetchLocalContext createFetchContext(FetchReq req, Action action){
        return new DefaultFetchContext(req, action);
    }

    protected ActionContext createActionContext(ActionReq req, Action action){
        return new DefaultActionContext(req, action);
    }

}

