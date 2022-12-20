package com.xigaiou.xigaiouproject.common.gql;

import java.util.Map;

public interface ActionReq<T> {
    T getInput();

    EngineTypeEnum getEngineType();

    /**
     * getVarMap -> getMap
     * @return
     */
    Map<String, Object> getMap();

    /**
     * existsVar -> existsKey
     * @param key
     * @return
     */
    boolean existsKey(String key);

    /**
     * getVar -> getKey
     * @param key
     * @return
     * @param <T>
     */
    <T> T getKey(String key);

    /**
     * getVar -> getKey
     * @param key
     * @param value
     * @return
     * @param <T>
     */
    <T> T getKey(String key, T value);

    /**
     * setVar -> setMap
     * @param key
     * @param value
     */
    void setMap(String key, Object value);
}
