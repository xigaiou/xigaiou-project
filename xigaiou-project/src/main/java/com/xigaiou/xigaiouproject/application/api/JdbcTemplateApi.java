package com.xigaiou.xigaiouproject.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcTemplateApi {
    /**
     * Spring Boot 默认提供了数据源，默认提供了 org.springframework.jdbc.core.JdbcTemplate
     * JdbcTemplate 中会自己注入数据源，用于简化 JDBC操作
     * 还能避免一些常见的错误,使用起来也不用再自己来关闭数据库连接
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询employee表中所有数据
    //List 中的1个 Map 对应数据库的 1行数据
    //Map 中的 key 对应数据库的字段名，value 对应数据库的字段值

    @GetMapping("/list")
    public List<Map<String, Object>> list(){
        String sql = "select * from employees";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    //新增一个用户
    @GetMapping("/add")
    public String addEmployee(){
        //插入语句，注意时间问题
        String sql = "insert into employees(employee_id, first_name, last_name, email" +
                ", phone_number, hire_date, job_id, salary, commission_pct, " +
                "manager_id, department_id)" +
                "values ('207', '小林', '美幸', '123@qq.com', '19829371027',"+
                "sysdate" +
                ", 'AD_PRES', '28000', null, null, '90')";
        jdbcTemplate.update(sql);
        //查询
        return "insert success";
    }

    //修改用户信息
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") int id){
        //插入语句
        String sql = "update employees set last_name=?,email=? where employee_id="+id;
        //数据
        Object[] objects = new Object[2];
        objects[0] = "有紀";
        objects[1] = "1251243@qq.com";
        jdbcTemplate.update(sql, objects);
        //查询
        return "update success";
    }

    //删除用户
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id){
        //插入语句
        String sql = "delete from employees where employee_id=?";
        jdbcTemplate.update(sql, id);
        //查询
        return "delete success";
    }
}
