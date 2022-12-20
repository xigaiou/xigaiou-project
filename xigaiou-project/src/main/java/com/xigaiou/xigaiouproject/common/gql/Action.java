package com.xigaiou.xigaiouproject.common.gql;

public interface Action <T>{
    String getNs();

    String getName();

    String getDataId();

    String getDesc();

    /**
     * 尝试将Req扩充为Env?有上下文变量存了fetcher，不知道行不行
     * @param actionEnv actionEnv
     * @return T
     */
    T execute(ActionEnv actionEnv);

    default void afterInit(){}
}
