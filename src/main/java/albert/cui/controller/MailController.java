package albert.cui.controller;

import albert.cui.utils.MailUtils;
import freemarker.template.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author albert.cui
 * @date 2019/2/24 22:50
 */
@Controller("mailController")
@RequestMapping("mail")
public class MailController {
    @Autowired
    @Qualifier(value = "javaMailSender")
    private JavaMailSender mailSender;
    @Resource
    private Configuration configuration;
    @Resource
    private VelocityEngine velocityEngine;

    @RequestMapping("send/freemarker")
    @RequiresPermissions("staff")
    @ResponseBody
    public String sendMailByFreeMarker() {
        MailUtils utils = new MailUtils();
        String result = utils.sendMailByFreeMarker("albert.cui再次来信",
                "C:/Users/lyCui/Pictures/Camera Roll/idea.jpg", "13255425825@163.com",
                mailSender, configuration);
        return result;
    }

    @RequestMapping("send/velocity")
    @ResponseBody
    public String sendMailByVelocity() {
        MailUtils utils = new MailUtils();
        String result = utils.sendMailByVelocity("albert.cui再次来信",
                "C:/Users/lyCui/Pictures/Camera Roll/idea.jpg", "13255425825@163.com",
                mailSender, velocityEngine);
        return result;
    }
}
