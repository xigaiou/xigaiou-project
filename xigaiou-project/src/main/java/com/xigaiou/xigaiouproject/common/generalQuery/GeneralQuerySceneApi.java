package com.xigaiou.xigaiouproject.common.generalQuery;

import com.alibaba.fastjson.JSONObject;
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

    @GetMapping("/infos/_query-by-employee-id")
    public JSONObject queryInfosByEmployeeId(String employeeId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("EMPLOYEE_ID", employeeId);
        JSONObject result = generalQueryAppService.execEmployeeInfoSceneQueryFetch(FIELD_BASE_INFO, paramMap);
        return result;
    }
}
