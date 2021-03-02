import com.miles.oa.biz.DepartmentBiz;
import com.miles.oa.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/6 23:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-web.xml")
public class TestWeb {
    @Resource
    private DepartmentBiz departmentBiz;
    @Test
    public void testBiz(){
        List<Department> departments = departmentBiz.getAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
}
