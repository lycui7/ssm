package albert.cui.service;

import albert.cui.entity.Employee;

/**
 * @author albert.cui
 * @date 2019/2/23 18:07
 */
public interface  UserService {
    Employee getUser(Integer userId);
}
