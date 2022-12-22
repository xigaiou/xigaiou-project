package com.xigaiou.xigaiouproject.application.service;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.domain.entity.EmployeesInfoSceneConf;
import com.xigaiou.xigaiouproject.domain.service.EmployeesInfoSceneConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通用查询AppService
 * @author 西盖欧
 * @date 2022-12-21
 */
@Service
public class GeneralQueryAppService {
    @Autowired
    private EmployeesInfoSceneConfService employeesInfoSceneConfService;

    /**
     * 处理返回值为单条的
     * @param sceneCode sceneCode
     * @param paramMap paramMap
     * @return JSONObject
     */
    public JSONObject execFetch(String sceneCode, Map<String, Object> paramMap){
        //1.拿出场景配置(用的wrapper，走baseMapper)
        List<EmployeesInfoSceneConf> list = employeesInfoSceneConfService.getSceneConfBySceneCode(sceneCode, paramMap);

        //2.根据场景配置查询数据(用的JDBC)
        return employeesInfoSceneConfService.execFetch(list, paramMap);
    }

    /**
     * 处理返回值为多条的
     * @param sceneCode sceneCode
     * @param paramMap paramMap
     * @return List<JSONObject>
     */
    public List<JSONObject> execBatchFetch(String sceneCode, Map<String, Object> paramMap){
        //1.拿出场景配置(用的wrapper，走baseMapper)
        List<EmployeesInfoSceneConf> list = employeesInfoSceneConfService.getSceneConfBySceneCode(sceneCode, paramMap);

        //2.根据场景配置查询数据(用的JDBC)
        return employeesInfoSceneConfService.execBatchFetch(list, paramMap);
    }
}
