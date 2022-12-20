package com.xigaiou.xigaiouproject.common.gql;

public interface ActionEnv {
    ActionContext getContext();

    default <T extends ActionReq> T getActionReq(){
        return this.getContext().getActionReq();
    }

    <T> T getArg(String arg);
}
