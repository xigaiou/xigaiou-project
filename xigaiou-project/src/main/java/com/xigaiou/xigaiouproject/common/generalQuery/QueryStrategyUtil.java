package com.xigaiou.xigaiouproject.common.generalQuery;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式工具类
 * @author xigaiou
 * @date 2022-12-05
 */
@Component
public class QueryStrategyUtil {

    /*

    *//**
     * INST
     *//*
    private static QueryStrategyUtil INST;

    *//**
     * 获取实例
     * @return INST
     *//*
    public static QueryStrategyUtil getInstance(){
        return INST;
    }

    *//**
     * 通用查询-指定数据源
     *//*
    @Getter
    private Map<String, GeneralSearchEmployeesInfoInterface> generalSearchEmployeesInfoMap;

    public QueryStrategyUtil(@Autowired GeneralSearchEmployeesInfoInterface[] generalSearchEmployeesInfos){
        generalSearchEmployeesInfoMap = new HashMap<>(generalSearchEmployeesInfos.length);
        for(GeneralSearchEmployeesInfoInterface one : generalSearchEmployeesInfos){
            generalSearchEmployeesInfoMap.put(one.getDataSrc(), one);
        }
    }

    */

}
