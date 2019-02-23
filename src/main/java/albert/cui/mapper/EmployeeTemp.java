package albert.cui.mapper;

import albert.cui.entity.Employee;

/**
 * @author albert.cui
 * @date 2019/2/24 1:12
 */
public interface EmployeeTemp {
    Employee selectByPrimaryKey(Integer id);
}
