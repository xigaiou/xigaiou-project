package com.xigaiou.xigaiouproject.domain.service;

import com.xigaiou.xigaiouproject.domain.entity.Employees;
import com.xigaiou.xigaiouproject.domain.repository.EmployeesRepository;
import com.xigaiou.xigaiouproject.infrastructure.persistence.EmployeesRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeesService {
    @Autowired
    private EmployeesRepositoryImpl employeesRepository;

    public List<Employees> getEmployees(){
        return employeesRepository.getEmployeesByList();
    }

    public void insertEmployee(Employees employee){
        employeesRepository.insertEmployee(employee);
    }

    public Employees getEmployee(String id){
        return employeesRepository.getEmployee(id);
    }

    public void deleteEmployee(String id){
        employeesRepository.deleteEmployee(id);
    }
}
