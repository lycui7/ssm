package albert.cui.mapper;

import albert.cui.entity.Employee;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author albert.cui
 * @date 2019/2/24 0:30
 */
@Repository
public class EmployeeDao {
    @Resource
    SqlSessionTemplate sqlSessionTemplate;
    public Employee getUser(Integer userId) {
        return  sqlSessionTemplate.selectOne(EmployeeMapper.class.getName()+".selectByPrimaryKey",1);
    }
}
