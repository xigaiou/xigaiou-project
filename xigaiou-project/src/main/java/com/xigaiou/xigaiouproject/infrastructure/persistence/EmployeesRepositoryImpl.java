package com.xigaiou.xigaiouproject.infrastructure.persistence;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xigaiou.xigaiouproject.domain.entity.Employees;
import com.xigaiou.xigaiouproject.domain.repository.EmployeesRepository;
import com.xigaiou.xigaiouproject.infrastructure.mapper.EmployeesMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesRepositoryImpl extends ServiceImpl<EmployeesMapper, Employees>
        implements EmployeesRepository {
    public List<Employees> getEmployeesByList() {
        return baseMapper.getEmployees();
    }

    public void insertEmployee(Employees employee){
        baseMapper.save(employee);
    }

    public Employees getEmployee(String id){
        return baseMapper.get(id);
    }

    public void deleteEmployee(String id){
        baseMapper.delete(id);
    }
}
