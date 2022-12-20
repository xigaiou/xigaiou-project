package com.xigaiou.xigaiouproject.common.gql;

public abstract class BaseFetcher<T> extends BaseAction<T> implements Fetcher<T>{
    private boolean fetchUniqueRow;

    public BaseFetcher(String name){
        this("", name, "");
    }

    public BaseFetcher(String ns, String name){
        this(ns, name, "");
    }

    public BaseFetcher(String ns, String name, String desc){
        super(ns, name, desc);
    }

    /**
     * 故意通过强制类型转换来调用入参为FetchEnv的方法
     * @param actionEnv actionEnv
     * @return T
     */
    protected final T onExecute(ActionEnv actionEnv){
        return onExecute((FetchEnv)actionEnv);
    }

    /**
     * 其实并没有实现后置拦截器的功能，只是提供了一个受保护的抽象方法,以下几个同理
     * @param fetcherEnv fetcherEnv
     * @return T
     */
    protected abstract T onExecute(FetchEnv fetcherEnv);

    public String getGraphFieldName(){
        return getDataId().replaceAll("[^\\d\\w]+", "_");
    }

    protected final boolean checkInput(ActionEnv actionEnv){
        return this.checkInput((FetchEnv)actionEnv);
    }

    protected boolean checkInput(FetchEnv fetchEnv){
        if (null == fetchEnv) {
            throw new IllegalArgumentException("fetchEnv is null");
        }
        return true;
    }

    protected final T handleResult(ActionEnv actionEnv, T result){
        return handleResult((FetchEnv)actionEnv, result);
    }

    protected T handleResult(FetchEnv fetchEnv, T result){
        if (null == fetchEnv) {
            throw new IllegalArgumentException("fetchEnv is null");
        }
        return result;
    }

    public BaseFetcher<T> setActionInterceptor(ActionInterceptor interceptor) {
        super.setActionInterceptor(interceptor);
        return this;
    }

    public void setFetchUniqueRow(boolean fetchUniqueRow){
        this.fetchUniqueRow = fetchUniqueRow;
    }

    public boolean isFetchUniqueRow(){
        return fetchUniqueRow;
    }
}
