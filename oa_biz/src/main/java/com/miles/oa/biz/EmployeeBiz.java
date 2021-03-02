package com.miles.oa.biz;

import com.miles.oa.entity.Department;
import com.miles.oa.entity.Employee;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/6 22:49
 */
public interface EmployeeBiz {
    void add(Employee employee);

    void edit(Employee employee);

    void remove(String sn);

    Employee get(String sn);

    List<Employee> getAll();
}
