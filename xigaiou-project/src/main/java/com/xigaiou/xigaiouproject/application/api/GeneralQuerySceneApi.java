package com.xigaiou.xigaiouproject.application.api;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.application.service.GeneralQueryAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Valid
@Slf4j
@RequestMapping("/general-query")
public class GeneralQuerySceneApi {
    @Autowired
    private GeneralQueryAppService generalQueryAppService;

    private final String FIELD_BASE_INFO = "base-info";

    /**
     * 员工基本信息查询
     * @param employeeId employeeId
     * @return JSONObject
     */
    @GetMapping("/infos/_query-by-employee-id")
    public JSONObject queryInfosByEmployeeId(String employeeId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeId", employeeId);
        JSONObject result = generalQueryAppService.execEmployeeInfoSceneQueryFetch(FIELD_BASE_INFO, paramMap);
        return result;
    }
}
