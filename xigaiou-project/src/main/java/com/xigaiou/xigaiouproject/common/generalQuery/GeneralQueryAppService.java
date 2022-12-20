package com.xigaiou.xigaiouproject.common.generalQuery;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.common.gql.GeneralQueryFetchReq;
import com.xigaiou.xigaiouproject.common.gql.GqlService;
import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeneralQueryAppService {
    private GqlService gqlService;

    private EmployeesInfoSceneConfService employeesInfoSceneConfService;

    public JSONObject execEmployeeInfoSceneQueryFetch(String sceneCode, Map<String, Object> paramMap){
        GeneralQueryFetchReq fetchReq = new GeneralQueryFetchReq(sceneCode, paramMap);
        //1.拿出场景配置(用的wrapper)
        List<EmployeesInfoSceneConf> list = ((PageRows<EmployeesInfoSceneConf>) gqlService.executeFetch(fetchReq).
                getFineData()).getList();
        //2.根据场景配置查询数据(用的JDBC)
        JSONObject result = employeesInfoSceneConfService.getOneEmployeeInfo(list, paramMap);
        return result;
    }
}
