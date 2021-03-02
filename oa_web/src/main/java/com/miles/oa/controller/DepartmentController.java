package com.miles.oa.controller;

import com.miles.oa.biz.DepartmentBiz;
import com.miles.oa.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @date 2021/2/6 23:06
 */
@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentBiz departmentBiz;

    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        List<Department> departments = departmentBiz.getAll();
        map.put("list", departments);
        return "department_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("department", new Department());
        return "department_add";
    }

    @RequestMapping("/add")
    public String add(Department department) {
        departmentBiz.add(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_update", params = "sn")
    public String toUpdate(String sn, Map<String, Object> map) {
        map.put("department", departmentBiz.get(sn));
        return "department_update";
    }

    @RequestMapping("/update")
    public String update(Department department) {
        departmentBiz.edit(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(String sn) {
        departmentBiz.remove(sn);
        return "redirect:list";
    }
}
