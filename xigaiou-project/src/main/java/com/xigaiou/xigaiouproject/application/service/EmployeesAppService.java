package com.xigaiou.xigaiouproject.application.service;

import com.xigaiou.xigaiouproject.domain.entity.Employees;
import com.xigaiou.xigaiouproject.domain.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EmployeesAppService {

    @Autowired
    private EmployeesService employeesService;

    public List<Employees> getEmployeesByList(){
        return employeesService.getEmployees();
    }

    public void insertEmployee(Employees employee){
        employee.setHireDate(new Date());
        employeesService.insertEmployee(employee);
    }

    public Employees getEmployee(String id){
        return employeesService.getEmployee(id);
    }

    public void deleteEmployee(String id){
        employeesService.deleteEmployee(id);
    }
}
