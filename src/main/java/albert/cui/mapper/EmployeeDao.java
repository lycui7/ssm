package albert.cui.mapper;

import albert.cui.entity.Employee;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("cache_users")
    public Employee getUser(Integer userId) {
        return  sqlSessionTemplate.selectOne(EmployeeMapper.class.getName()+".selectByPrimaryKey",userId);
    }
}
