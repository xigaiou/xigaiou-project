package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.MapUtil;
import org.jetbrains.annotations.NotNull;

import static com.xigaiou.xigaiouproject.common.gqlSupport.MapUtil.newMap;

public final class GeneralEngine extends BaseEngine{
    public GeneralEngine(GqlService gqlService){
        super(gqlService);
    }

    public EngineTypeEnum getEngineType(){
        return EngineTypeEnum.GENERAL;
    }

    public FetchResult fetch(FetchReq fetchReq){
        if(!(fetchReq instanceof GeneralFetchReq)){
            System.out.println("the fetchReq is not instanceof GeneralFetchReq");
            throw new RuntimeException("the fetchReq is not instanceof GeneralFetchReq");
        }
        GeneralFetchReq generalFetchReq = (GeneralFetchReq) fetchReq;
        Fetcher fetcher = getFetcher(generalFetchReq.getDataId());
        GeneralFetchEnv env = new GeneralFetchEnv(createFetchContext(fetchReq, fetcher));
        Object result = doFetch(fetcher, env);
        return new DefaultFetchResult(env.getFetchContext(), newMap(generalFetchReq.getDataId(), result));
    }

    protected Object doFetch(@NotNull Fetcher fetcher, GeneralFetchEnv env){
        Object result = fetcher.execute(env);
        return result;
    }
    //handleResult作用为mask和fl掏出来变成json

    public ActionResult execute(ActionReq req){
        if(req instanceof FetchReq){
            return fetch((FetchReq)req);
        } else {
            if(!(req instanceof GeneralActionReq)){
                throw new RuntimeException("the actionReq is not instanceof GeneralActionReq");
            }
            GeneralActionReq generalActionReq = (GeneralActionReq)req;
            Action action = getAction(generalActionReq.getDataId());
            ActionEnv env = new GeneralActionEnv(createActionContext(req, action));
            Object result = action.execute(env);
            return new DefaultActionResult(env.getContext(), MapUtil.newMap(generalActionReq.getDataId(), result));
        }
    }
}
