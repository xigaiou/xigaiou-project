package com.xigaiou.xigaiouproject.common.generalQuery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeesInfoSceneConfService {
    @Autowired
    private EmployeesInfoSceneConfRepository repository;

    public List<EmployeesInfoSceneConf> getSceneConfBySceneCode(String sceneCode){
        List<EmployeesInfoSceneConf> list = repository.getSceneConfBySceneCode(sceneCode);
        if(list.size() < 1){
            System.out.println("未找到该场景");
            throw new RuntimeException("未找到该场景");
        }
        return list;
    }

    public JSONObject getOneEmployeeInfo(List<EmployeesInfoSceneConf> list, Map<String, Object> paramMap){
        JSONObject result = new JSONObject();
        list.stream().filter(e -> StringUtils.isNotBlank(e.getQryTeplt()))
                .forEach(e ->{
                    GeneralSearchEmployeesInfoInterface generalSearchEmployeesInfo =
                            QueryStrategyUtil.getInstance().getGeneralSearchEmployeesInfoMap().get(e.getDataSrc());
                    result.putAll((JSONObject) JSON.toJSON(generalSearchEmployeesInfo.fetchOne(e, paramMap)));
                });
        return result;
    }
}
