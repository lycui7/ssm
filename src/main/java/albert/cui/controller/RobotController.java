package albert.cui.controller;

import albert.cui.entity.Robot;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author albert.cui
 * @date 2019/3/3 23:28
 */
@Controller
@RequestMapping("robot")
public class RobotController {
    @RequestMapping("login")
    public String login(@RequestParam(value = "username") String name,
                        @RequestParam(value = "password") String pwd,
                        @RequestParam(value = "checkcode") String code,
                            HttpServletRequest request) {
        //得到生成的验证码
        String key = (String) request.getSession().getAttribute("key");
        //判断验证码是否正确
        if(StringUtils.isNotBlank(key) && key.equals(code)) {
            //验证码正确
            Subject subject = SecurityUtils.getSubject();
            //构造一个用户名密码令牌
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, pwd);
            try{
                subject.login(usernamePasswordToken);
            }catch (UnknownAccountException e){
                e.printStackTrace();
                return "login";
            }catch (Exception e){
                e.printStackTrace();
                return "login";
            }
            //获取认证信息对象中存储的user对象
            Robot robot = (Robot) subject.getPrincipal();
            request.getSession().setAttribute("loginRobot",robot);
            return "success";
        }else {
            //验证码错误
            return "login";
        }
    }
}
