package com.miles.oa.dao;

import com.miles.oa.entity.Department;
import com.miles.oa.entity.Employee;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/3 21:11
 */
@Repository("employeeDao")
public interface EmployeeDao {

    @Insert("insert into employee (sn,password,name,department_sn,post) values(#{sn},#{password},#{name},#{departmentSn},#{post})")
    void insert(Employee employee);

    @Update("update employee set name=#{name},password=#{password},department_sn=#{departmentSn},post=#{post} where sn=#{sn}")
    void update(Employee employee);

    @Delete("delete from employee where sn=#{sn}")
    void delete(String sn);

    @Results(
            id = "employeeDepartment",
            value = {
                    @Result(column = "sn", property = "sn", id = true),
                    @Result(column = "password", property = "password"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "department_sn", property = "departmentSn"),
                    @Result(column = "post", property = "post"),
                    @Result(column = "department_sn", property = "department", one = @One(select = "com.miles.oa.dao.DepartmentDao.select", fetchType = FetchType.DEFAULT))
            }
    )
    @Select("select * from employee where sn=#{sn}")
    Employee select(String sn);

    @ResultMap("employeeDepartment")
    @Select("select * from employee")
    List<Employee> selectAll();

    @Select("<script>select * from employee <where> <if test='dsn!=null'>and department_sn=#{dsn}</if><if test='post!=null'>and post=#{post}</if></where></script>")
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}
