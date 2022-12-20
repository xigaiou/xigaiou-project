package com.xigaiou.xigaiouproject.common.gql;

public abstract class BaseFetchInterceptor<T> implements ActionInterceptor<T> {

    public BaseFetchInterceptor(){}

    /**
     * 故意通过强制类型转换来调用入参为FetchEnv的方法
     * @param actionEnv actionEnv
     */
    public final void executeBefore(ActionEnv actionEnv){
        this.executeBefore((FetchEnv)actionEnv);
    }

    /**
     * 其实并没有实现前置拦截器的功能，只是提供了一个受保护的抽象方法
     * @param fetchEnv fetchEnv
     */
    protected abstract void executeBefore(FetchEnv fetchEnv);

    /**
     * 故意通过强制类型转换来调用入参为FetchEnv的方法
     * @param result result
     * @param actionEnv actionEnv
     */
    public final T executeAfter(T result, ActionEnv actionEnv){
        return this.executeAfter(result, (FetchEnv)actionEnv);
    }

    /**
     * 其实并没有实现后置拦截器的功能，只是提供了一个受保护的抽象方法
     * @param fetchEnv fetchEnv
     */
    protected abstract T executeAfter(T result, FetchEnv fetchEnv);
}
