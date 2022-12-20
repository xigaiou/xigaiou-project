package com.xigaiou.xigaiouproject.common.gql;

/**
 * 用于记录Action的上下文信息，考虑到UserSession和 AppSession略去，不知道能不能把这个简化成一个map
 */
public interface ActionContext {
    Action getAction();

    <T extends ActionReq> T getActionReq();

    boolean containsKey(String key);

    Object get(String key);

    Object put(String key, Object value);

    //UserSession 和 AppSession的获取函数略
}
