package com.xigaiou.mvc.controller;

import com.xigaiou.mvc.bean.Employee;
import com.xigaiou.mvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/e/", method = RequestMethod.GET)
    public String index(){
        return "index-employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployeeList(Model model){
        Collection<Employee> employeeList = employeeDao.getAll();
        model.addAttribute("employeeList", employeeList);
        return "employee_list";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employee";
    }
}
