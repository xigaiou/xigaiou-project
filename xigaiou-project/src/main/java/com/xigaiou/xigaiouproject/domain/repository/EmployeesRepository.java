package com.xigaiou.xigaiouproject.domain.repository;

import com.xigaiou.xigaiouproject.domain.entity.Employees;

import java.util.List;

public interface EmployeesRepository{
    List<Employees> getEmployeesByList();
    void insertEmployee(Employees employee);
    Employees getEmployee(String id);
    void deleteEmployee(String id);
}
