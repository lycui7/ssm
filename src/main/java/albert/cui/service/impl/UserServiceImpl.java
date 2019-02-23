package albert.cui.service.impl;

import albert.cui.entity.Employee;
import albert.cui.mapper.EmployeeMapper;
import albert.cui.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author albert.cui
 * @date 2019/2/23 18:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    EmployeeMapper employeeMapper;
    @Override
    public Employee getUser(Integer userId) {
        return employeeMapper.selectByPrimaryKey(userId);
    }
}
