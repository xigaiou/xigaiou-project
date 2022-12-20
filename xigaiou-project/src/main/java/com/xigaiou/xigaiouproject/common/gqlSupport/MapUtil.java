package com.xigaiou.xigaiouproject.common.gqlSupport;

import java.util.HashMap;
import java.util.Map;

public final class MapUtil {
    public static Map<String, Object> newMap(String key, Object value){
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
