package com.xigaiou.xigaiouproject.common.gql;

import java.util.Map;

public interface ActionResult {
    ActionContext getContext();

    Map<String, Object> getDataMap();

    <T> T getFirstData();

    default <T> T getDataItem(String key) {
        return (T) this.getDataMap().get(key);//自动填充了 Object 到 (T)的强制转换
    }
}
