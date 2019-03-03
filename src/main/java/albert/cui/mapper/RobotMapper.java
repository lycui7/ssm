package albert.cui.mapper;

import albert.cui.entity.Resource;
import albert.cui.entity.Robot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author albert.cui
 * @date 2019/3/3 15:58
 */
public interface RobotMapper {
    /**
     * 通过名字来查询一条数据
     * @param robotName
     * @return
     */
    Robot findRobotByName(@Param(value = "name") String robotName);

    /**
     * 通过robot主键得到resource
     * @param robotId
     * @return
     */
    List<Resource> findResources(@Param(value = "robotId") String robotId);

    /**
     * 插入一条数据
     * @param robot
     */
    void insert(Robot robot);
}
