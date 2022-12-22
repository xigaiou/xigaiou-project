package com.xigaiou.xigaiouproject.application.api;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.application.service.GeneralQueryAppService;
import com.xigaiou.xigaiouproject.common.generalQuery.GeneralQueryConstant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @author 西盖欧
 * @date 2022-12-21
 */
@RestController
@Valid
@Slf4j
@RequestMapping("/general-query")
public class GeneralQuerySceneApi {
    @Autowired
    private GeneralQueryAppService generalQueryAppService;

    /**
     * 员工基本信息查询
     * 返回值为单条
     * @param employeeId employeeId
     * @return JSONObject
     */
    @ApiOperation(value = "查询员工基本信息", notes = "走场景查询，返回值为单条")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "员工号", required = true, dataType = "String")
    })
    @GetMapping("/"+ GeneralQueryConstant.BASE_INFO+"/_query-by-employee-id")
    public JSONObject queryInfosByEmployeeId(String employeeId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeId", employeeId);
        return generalQueryAppService.execFetch(GeneralQueryConstant.BASE_INFO, paramMap);
    }

    /**
     * 部门薪资查询
     * 返回值为多条
     * @param departmentId departmentId
     * @return List<JSONObject>
     */
    @ApiOperation(value = "查询部门薪资", notes = "走场景查询，返回值为多条")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "departmentId", value = "部门号", required = true, dataType = "String")
    })
    @GetMapping("/"+GeneralQueryConstant.DEPARTMENT_SALARY+"/_query-by-department-id")
    public List<JSONObject> querySalaryByDepartmentId(String departmentId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("departmentId", departmentId);
        return generalQueryAppService.execBatchFetch(GeneralQueryConstant.DEPARTMENT_SALARY, paramMap);
    }
}
