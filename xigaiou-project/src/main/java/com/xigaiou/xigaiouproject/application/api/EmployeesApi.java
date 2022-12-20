package com.xigaiou.xigaiouproject.application.api;

import com.xigaiou.xigaiouproject.application.service.EmployeesAppService;
import com.xigaiou.xigaiouproject.domain.entity.Employees;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 员工表的操作
 * @author 吴子敬
 * @date 2022-08-18
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class EmployeesApi {
    /**
     * employeesAppService
     */
    @Autowired
    private EmployeesAppService employeesAppService;

    /**
     * 查看整个Employees列表
     * @return List<Employees>
     */
    @ApiOperation(value = "label", notes = "查看整个Employees列表")
    @GetMapping("/getEmployees")
    public List<Employees> getEmployees(){
        return employeesAppService.getEmployeesByList();
    }

    /**
     * 插入一条员工信息
     * @param employee employee
     */
    @ApiOperation(value = "label", notes = "插入一条员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employee", value = "员工",dataTypeClass = Long.class, required = true, dataType = "Employees")
    })
    @PostMapping("/insertEmployee")
    public String insertEmployee(@RequestBody Employees employee){
        employeesAppService.insertEmployee(employee);
        return "insert success";
    }

    /**
     * 输入员工id获取指定员工信息
     * @param id id
     * @return Employees
     */
    @ApiOperation(value = "label", notes = "输入员工id获取指定员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "员工id",dataTypeClass = Long.class, required = true, dataType = "String")
    })
    @GetMapping("/getEmployee")
    public Employees getEmployee(@RequestParam String id){
        return employeesAppService.getEmployee(id);
    }

    /**
     * 输入员工id删除指定员工信息
     * @param id id
     */
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam String id){
        employeesAppService.deleteEmployee(id);
        return "delete success";
    }
}
