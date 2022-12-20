package com.xigaiou.xigaiouproject.common.gql;

public interface ActionInterceptor<T> {

    void executeBefore(ActionEnv var1);

    T executeAfter(T result, ActionEnv actionEnv);
}
