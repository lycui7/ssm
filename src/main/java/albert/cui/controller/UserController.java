package albert.cui.controller;

import albert.cui.entity.Employee;
import albert.cui.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author albert.cui
 * @date 2019/2/23 18:10
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public @ResponseBody Employee getOneEmployee(@PathVariable(value = "id") Integer id) {
       return userService.getUser(id);
    }
}
