package com.miles.oa.biz;

import com.miles.oa.entity.Department;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/6 22:49
 */
public interface DepartmentBiz {
    void add(Department department);

    void edit(Department department);

    void remove(String sn);

    Department get(String sn);

    List<Department> getAll();
}
