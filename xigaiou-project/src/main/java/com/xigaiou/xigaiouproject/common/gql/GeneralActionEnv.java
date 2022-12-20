package com.xigaiou.xigaiouproject.common.gql;

import lombok.Getter;

public class GeneralActionEnv implements ActionEnv{
    @Getter
    private ActionContext context;

    public GeneralActionEnv(ActionContext context){
        this.context = context;
    }

    public <T> T getArg(String key){
        return (T) getActionReq().getKey(key);
    }

    protected <T extends GeneralActionReq> T getGeneralReq(){
        return getActionReq();
    }
}
