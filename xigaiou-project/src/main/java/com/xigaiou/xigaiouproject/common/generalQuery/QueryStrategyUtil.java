package com.xigaiou.xigaiouproject.common.generalQuery;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式工具类
 * @author 西盖欧
 * @date 2022-12-05
 */
@Component
public class QueryStrategyUtil {


    /**
     * INST
     */
    private static QueryStrategyUtil INST;

    /**
     * 获取实例
     * @return INST
     */
    public static QueryStrategyUtil getInstance(){
        return INST;
    }

    /**
     * 通用查询-指定数据源
     */

    @Getter
    private final Map<String, GeneralSearchEmployeesInfoInterface<JSONObject>> generalSearchEmployeesInfoMap;

    public QueryStrategyUtil(@Autowired GeneralSearchEmployeesInfoInterface<JSONObject>[] generalSearchEmployeesInfos){
        generalSearchEmployeesInfoMap = new HashMap<>(1);
        //generalSearchEmployeesInfoMap = new HashMap<>(generalSearchEmployeesInfos.length);
        generalSearchEmployeesInfoMap.put(generalSearchEmployeesInfos[0].getDataSrc(), generalSearchEmployeesInfos[0]);
        /*
        for(GeneralSearchEmployeesInfoInterface one : generalSearchEmployeesInfos){
            generalSearchEmployeesInfoMap.put(one.getDataSrc(), one);
        }
        */
        INST = this;
    }


}
