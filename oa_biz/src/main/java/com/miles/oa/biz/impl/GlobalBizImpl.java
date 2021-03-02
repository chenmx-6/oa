package com.miles.oa.biz.impl;

import com.miles.oa.biz.GlobalBiz;
import com.miles.oa.dao.EmployeeDao;
import com.miles.oa.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Miles
 * @date 2021/2/7 21:23
 */
@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {
    @Resource
    private EmployeeDao employeeDao;
    public Employee login(String sn, String password) {
        Employee employee=null;
        Employee select = employeeDao.select(sn);
        if(select!=null){
            if(select.getPassword().equals(password)){
                employee=select;
            }
        }
        return employee;
    }

    public void changePassword(Employee employee) {
        employeeDao.update(employee);
    }
}
