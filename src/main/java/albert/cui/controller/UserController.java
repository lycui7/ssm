package albert.cui.controller;

import albert.cui.entity.Employee;
import albert.cui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author albert.cui
 * @date 2019/2/23 18:10
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public @ResponseBody Employee getOneEmployee(@PathVariable(value = "id") Integer id) {
       return userService.getUser(id);
    }
    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    public @ResponseBody Employee findOneEmployee(@PathVariable(value = "id") Integer id) {
        return userService.findUser(id);
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public @ResponseBody String deleteOneEmployee(@PathVariable(value = "id") Integer id) {
        Integer result = userService.deleteUser(id);
        if(result>0) {
            return "statusCode:200,statusText:成功";
        }else{
            return "statusCode:400,statusText:失败";
        }
    }
    @RequestMapping(value = "search",method = RequestMethod.GET)
    public @ResponseBody Employee searchOneEmployee(@RequestParam(value = "id",defaultValue = "1") Integer id) {
        return userService.getUser(id);
    }
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public ModelAndView testController(@RequestParam(value = "id") Integer userId,
                                       @RequestParam(value = "name") String userName) {
        Employee e = new Employee();
        e.setId(userId);
        e.setName(userName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("index");
        modelAndView.getModel().put("employee",e);
        return  modelAndView;
    }
}
