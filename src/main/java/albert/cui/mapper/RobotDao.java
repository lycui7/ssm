package albert.cui.mapper;

import albert.cui.entity.Resource;
import albert.cui.entity.Robot;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author albert.cui
 * @date 2019/3/3 15:47
 */
@Repository
public class RobotDao extends SqlSessionDaoSupport {
    /*自 mybatis-spring-1.2.0 以来， SqlSessionDaoSupport 的
    setSqlSessionTemplate 和 setSqlSessionFactory 两个方法上的 @Autowired 注解被删除，
    这就意味着继承于 SqlSessionDaoSupport 的 DAO 类，
    它们的对象不能被自动注入 SqlSessionFactory 或 SqlSessionTemplate 对象*/
    @Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
       super.setSqlSessionTemplate(sqlSessionTemplate);
    }
   public Robot getRobotByName(String robotName) {
        return this.getSqlSession().selectOne(RobotMapper.class.getName()+".findRobotByName",robotName);
   }
   public List<Resource> getResourcesForRobot(String robotId) {
       return this.getSqlSession().selectList(RobotMapper.class.getName()+".findResources",robotId);
   }
}
