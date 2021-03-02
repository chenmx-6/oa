package com.miles.oa.biz.impl;

import com.miles.oa.biz.EmployeeBiz;
import com.miles.oa.dao.EmployeeDao;
import com.miles.oa.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/7 19:46
 */
@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {
    @Resource
    private EmployeeDao employeeDao;
    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        Employee employee = employeeDao.select(sn);
        return employee;
    }

    public List<Employee> getAll() {
        List<Employee> employees = employeeDao.selectAll();
        return employees;
    }
}
