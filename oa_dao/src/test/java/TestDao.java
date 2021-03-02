import com.miles.oa.dao.DealRecorderDao;
import com.miles.oa.dao.DepartmentDao;
import com.miles.oa.dao.EmployeeDao;
import com.miles.oa.entity.DealRecord;
import com.miles.oa.entity.Department;
import com.miles.oa.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/3 21:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class TestDao {
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private DealRecorderDao dealRecorderDao;
    @Test
    public void testSelectAll(){
        List<Department> departments = departmentDao.selectAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    @Test
    public void testSelectAllEmployee(){
        List<Employee> employees = employeeDao.selectAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    @Test
    public void testSelectRecord(){
        List<DealRecord> dealRecords = dealRecorderDao.selectByClaimVoucher(25);
        for (DealRecord dealRecord : dealRecords) {
            System.out.println(dealRecord);
        }
    }
}
