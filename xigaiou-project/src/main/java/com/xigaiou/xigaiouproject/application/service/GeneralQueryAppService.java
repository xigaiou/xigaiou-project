package com.xigaiou.xigaiouproject.application.service;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.domain.entity.EmployeesInfoSceneConf;
import com.xigaiou.xigaiouproject.domain.service.EmployeesInfoSceneConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeneralQueryAppService {
    @Autowired
    private EmployeesInfoSceneConfService employeesInfoSceneConfService;

    public JSONObject execEmployeeInfoSceneQueryFetch(String sceneCode, Map<String, Object> paramMap){
        //1.拿出场景配置(用的wrapper，走baseMapper)
        List<EmployeesInfoSceneConf> list = employeesInfoSceneConfService.getSceneConfBySceneCode(sceneCode, paramMap);

        //3.根据场景配置查询数据(用的JDBC)
        JSONObject result = employeesInfoSceneConfService.getOneEmployeeInfo(list, paramMap);
        return result;
    }
}
