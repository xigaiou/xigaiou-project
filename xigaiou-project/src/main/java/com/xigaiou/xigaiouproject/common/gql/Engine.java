package com.xigaiou.xigaiouproject.common.gql;

public interface Engine {
    EngineTypeEnum getEngineType();

    FetchResult fetch(FetchReq var1);

    ActionResult execute(ActionReq var1);
}
