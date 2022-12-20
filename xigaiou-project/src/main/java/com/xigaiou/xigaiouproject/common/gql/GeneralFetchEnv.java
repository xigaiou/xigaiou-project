package com.xigaiou.xigaiouproject.common.gql;

public final class GeneralFetchEnv extends GeneralActionEnv implements FetchEnv {
    public GeneralFetchEnv(FetchLocalContext context){
        super(context);
    }

    protected GeneralFetchReq getGeneralFetchReq(){
        return (GeneralFetchReq) getGeneralReq();
    }

    public String getFl(){
        return getGeneralFetchReq().getFl();
    }
}
