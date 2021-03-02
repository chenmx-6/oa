package com.miles.oa.dao;

import com.miles.oa.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/3 21:11
 */
@Repository("departmentDao")
public interface DepartmentDao {
    @Insert("insert into department (sn,name,address) values(#{sn},#{name},#{address})")
    void insert(Department department);
    @Update("update department set name=#{name},address=#{address} where sn=#{sn}")
    void update(Department department);
    @Delete("delete from department where sn=#{sn}")
    void delete(String sn);
    @Select("select * from department where sn=#{sn}")
    Department select(String sn);
    @Select("select * from department")
    List<Department> selectAll();
}
