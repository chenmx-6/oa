package com.miles.oa.controller;

import com.miles.oa.biz.DepartmentBiz;
import com.miles.oa.biz.EmployeeBiz;
import com.miles.oa.entity.Employee;
import com.miles.oa.global.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @date 2021/2/6 23:06
 */
@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeBiz employeeBiz;
    @Resource
    private DepartmentBiz departmentBiz;
    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        List<Employee> list = employeeBiz.getAll();
        map.put("list", list);
        return "employee_list";
    }
    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("employee", new Employee());
        map.put("dlist",departmentBiz.getAll());
        map.put("plist", Constant.getPosts());
        return "employee_add";
    }
    @RequestMapping("/add")
    public String add(Employee employee){
        employeeBiz.add(employee);
        return "redirect:list";
    }
    @RequestMapping(value = "/to_update",params = "sn")
    public String toUpdate(String sn,Map<String,Object> map){
        map.put("employee",employeeBiz.get(sn));
        map.put("dlist",departmentBiz.getAll());
        map.put("plist", Constant.getPosts());
        return "employee_update";
    }
    @RequestMapping("/update")
    public String update(Employee employee){
        System.out.println(employee);
        employeeBiz.edit(employee);
        return "redirect:list";
    }
    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        employeeBiz.remove(sn);
        return "redirect:list";
    }
}
