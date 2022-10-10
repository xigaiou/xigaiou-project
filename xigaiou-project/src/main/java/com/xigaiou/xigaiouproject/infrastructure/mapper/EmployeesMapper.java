package com.xigaiou.xigaiouproject.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xigaiou.xigaiouproject.domain.entity.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeesMapper extends BaseMapper<Employees>{
    /**
     * 查询员工列表
     * @return List<Employees>
     */
    List<Employees> getEmployees();

    /**
     * 新增一个员工
     * @param employee employee
     */
    void save(Employees employee);

    /**
     * 通过id获得员工信息
     * @param id id
     * @return Employee
     */
    Employees get(String id);

    /**
     * 通过id删除员工
     * @param id id
     */
    void delete(String id);
}
