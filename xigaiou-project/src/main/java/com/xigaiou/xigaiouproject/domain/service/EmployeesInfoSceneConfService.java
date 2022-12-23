package com.xigaiou.xigaiouproject.domain.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.common.generalQuery.GeneralSearchEmployeesInfoInterface;
import com.xigaiou.xigaiouproject.common.generalQuery.QueryStrategyUtil;
import com.xigaiou.xigaiouproject.common.generalQuery.QueryUtils;
import com.xigaiou.xigaiouproject.domain.entity.EmployeesInfoSceneConf;
import com.xigaiou.xigaiouproject.domain.repository.EmployeesInfoSceneConfRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用查询Service
 * @author 西盖欧
 * @date 2022-12-21
 */
@Service
@Slf4j
public class EmployeesInfoSceneConfService {
    @Autowired
    private EmployeesInfoSceneConfRepository repository;

    /**
     * 找到查询场景对应的查询模板
     * @param sceneCode 场景代码
     * @param paramMap 入参
     * @return List<EmployeesInfoSceneConf>
     */
    public List<EmployeesInfoSceneConf> getSceneConfBySceneCode(String sceneCode, Map<String, Object> paramMap){
        List<EmployeesInfoSceneConf> list = repository.getSceneConfBySceneCode(sceneCode);
        if(list.size() < 1){
            System.out.println("未找到该场景");
            throw new RuntimeException("未找到该场景");
        }
        handleResult(list, paramMap);
        return list;
    }

    /**
     * 模板替换
     * 将查询模板替换为真正的入参
     * @param sceneConfList 场景配置list
     * @param paramMap      入参
     */
    protected void handleResult(List<EmployeesInfoSceneConf> sceneConfList, Map<String, Object> paramMap){
        sceneConfList.forEach(e -> {
            String qryTeplt = QueryUtils.parseQuery(e.getQryTeplt(), paramMap, true);
            e.setQryTeplt(qryTeplt);
        });
    }

    /**
     * 根据SQL语句查询oracle数据库
     * 返回为单条
     * @param list 场景配置list
     * @param paramMap 入参
     * @return JSONObject
     */
    public JSONObject execFetch(List<EmployeesInfoSceneConf> list, Map<String, Object> paramMap){
        JSONObject result = new JSONObject();
        list.stream().filter(conf -> StringUtils.isNotBlank(conf.getQryTeplt())).forEach(conf ->{
            GeneralSearchEmployeesInfoInterface<JSONObject> generalSearchEmployeesInfo =
                    QueryStrategyUtil.getInstance().getGeneralSearchEmployeesInfoMap().get(conf.getDataSrc());
            result.putAll((JSONObject) JSON.toJSON(generalSearchEmployeesInfo.fetchOne(conf, paramMap)));
        });
        return result;
    }

    /**
     * 根据SQL语句查询oracle数据库
     * 返回为多条
     * @param list 场景配置list
     * @param paramMap 入参
     * @return List<JSONObject>
     */
    public List<JSONObject> execBatchFetch(List<EmployeesInfoSceneConf> list, Map<String, Object> paramMap){
        List<JSONObject> result = new ArrayList<>(list.get(0).getBackCntLimit());
        list.stream().filter(conf -> StringUtils.isNotBlank(conf.getQryTeplt())).forEach(conf ->{
            GeneralSearchEmployeesInfoInterface<JSONObject> generalSearchEmployeesInfo =
                    QueryStrategyUtil.getInstance().getGeneralSearchEmployeesInfoMap().get(conf.getDataSrc());
            result.addAll( generalSearchEmployeesInfo.fetchList(conf, paramMap));
        });
        return result;
    }
}
