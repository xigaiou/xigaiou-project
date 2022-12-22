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

@Service
@Slf4j
public class EmployeesInfoSceneConfService {
    @Autowired
    private EmployeesInfoSceneConfRepository repository;

    public List<EmployeesInfoSceneConf> getSceneConfBySceneCode(String sceneCode, Map<String, Object> paramMap){
        List<EmployeesInfoSceneConf> list = repository.getSceneConfBySceneCode(sceneCode);
        if(list.size() < 1){
            System.out.println("未找到该场景");
            throw new RuntimeException("未找到该场景");
        }
        handleResult(list, paramMap);
        return list;
    }

    protected List<EmployeesInfoSceneConf> handleResult(List<EmployeesInfoSceneConf> sceneConfList,
                                                        Map<String, Object> paramMap){
        sceneConfList.stream().forEach(e -> {
            String qryTeplt = QueryUtils.parseQuery(e.getQryTeplt(), paramMap, true);
            e.setQryTeplt(qryTeplt);
        });
        return sceneConfList;
    }

    public JSONObject execFetch(List<EmployeesInfoSceneConf> list, Map<String, Object> paramMap){
        JSONObject result = new JSONObject();
        //stream.filter一般适用于list集合,主要作用就是模拟sql查询，从集合中查询想要的数据
        list.stream().filter(conf -> StringUtils.isNotBlank(conf.getQryTeplt()))
                .forEach(conf ->{
                    GeneralSearchEmployeesInfoInterface generalSearchEmployeesInfo =
                            QueryStrategyUtil.getInstance().getGeneralSearchEmployeesInfoMap().get(conf.getDataSrc());
                    result.putAll((JSONObject) JSON.toJSON(generalSearchEmployeesInfo.fetchOne(conf, paramMap)));
                });
        return result;
    }

    public List<JSONObject> execBatchFetch(List<EmployeesInfoSceneConf> list, Map<String, Object> paramMap){
        List<JSONObject> result = new ArrayList<>(list.get(0).getBackCntLimit());
        list.stream().filter(conf -> StringUtils.isNotBlank(conf.getQryTeplt()))
                .forEach(conf ->{
                    GeneralSearchEmployeesInfoInterface generalSearchEmployeesInfo =
                            QueryStrategyUtil.getInstance().getGeneralSearchEmployeesInfoMap().get(conf.getDataSrc());
                    result.addAll( generalSearchEmployeesInfo.fetchList(conf, paramMap));
                });
        return result;
    }
}
