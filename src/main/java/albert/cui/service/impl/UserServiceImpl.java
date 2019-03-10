package albert.cui.service.impl;

import albert.cui.entity.Employee;
import albert.cui.mapper.EmployeeDao;
import albert.cui.mapper.EmployeeMapper;
import albert.cui.mapper.EmployeeTemp;
import albert.cui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author albert.cui
 * @date 2019/2/23 18:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    EmployeeDao employeeDao;
    @Resource
    EmployeeTemp employeeTemp;
    @Resource
    EmployeeMapper employeeMapper;
    @Override
    public Employee getUser(Integer userId) {
        return employeeDao.getUser(userId);
    }

    @Override
    public Employee findUser(Integer userId) {
        return employeeTemp.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer deleteUser(Integer userId) {
        int result = employeeMapper.deleteByPrimaryKey(userId);
        try {
            int a = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
