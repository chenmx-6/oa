package com.miles.oa.biz;

import com.miles.oa.entity.Employee;

/**
 * @author Miles
 * @date 2021/2/7 21:22
 */
public interface GlobalBiz {
    Employee login(String sn,String password);
    void changePassword(Employee employee);
}
