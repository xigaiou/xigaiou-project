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
import java.util.List;
import java.util.Map;

/**
 * 通用查询（场景查询）
 * @author
 */
@RestController
@Valid
@Slf4j
@RequestMapping("/general-query")
public class GeneralQuerySceneApi {
    @Autowired
    private GeneralQueryAppService generalQueryAppService;
    /**
     * 员工基本信息场景
     */
    private final String FIELD_BASE_INFO = "base-info";

    /**
     * 部门薪资场景
     */
    private final String FIELD_DEPARTMENT_SALARY = "department-salary";

    /**
     * 员工基本信息查询
     * @param employeeId employeeId
     * @return JSONObject
     */
    @GetMapping("/infos/_query-by-employee-id")
    public JSONObject queryInfosByEmployeeId(String employeeId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeId", employeeId);
        JSONObject result = generalQueryAppService.execFetch(FIELD_BASE_INFO, paramMap);
        return result;
    }

    @GetMapping("/department/_query-by-department-id")
    public List<JSONObject> querySalaryByDepartmentId(String departmentId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("departmentId", departmentId);
        List<JSONObject> result = generalQueryAppService.execBatchFetch(FIELD_DEPARTMENT_SALARY, paramMap);
        return result;
    }
}
