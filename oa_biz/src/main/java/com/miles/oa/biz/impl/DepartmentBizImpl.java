package com.miles.oa.biz.impl;

import com.miles.oa.biz.DepartmentBiz;
import com.miles.oa.dao.DepartmentDao;
import com.miles.oa.entity.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/6 22:55
 */
@Service("departmentBiz")
public class DepartmentBizImpl implements DepartmentBiz {
    @Resource
    private DepartmentDao departmentDao;
    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(String sn) {
        departmentDao.delete(sn);
    }

    public Department get(String sn) {
        Department department = departmentDao.select(sn);
        return department;
    }

    public List<Department> getAll() {
        List<Department> departments = departmentDao.selectAll();
        return departments;
    }
}
