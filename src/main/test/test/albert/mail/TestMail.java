package albert.mail;

import albert.cui.utils.MailUtils;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.util.StatusViaSLF4JLoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * @author albert.cui
 * @date 2019/2/24 15:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mail.xml"})
public class TestMail {
    private final static Logger logger = LoggerFactory.getLogger(TestMail.class);
    @Autowired
    JavaMailSender mailSender;
    @Resource
    Configuration configuration;
    @Autowired
    VelocityEngine velocityEngine;
    @Test
    public void testSendHtmlMail() {
        MailUtils utils = new MailUtils();
        String result = utils.sendMail("<p>\uD83D\uDC16啊你，吃这么多</p>\n" +
                        "<p style=\"color:red\">爱你爱你爱你，随时都要一起</p>", "albert.cui再次来信",
                "C:/Users/lyCui/Pictures/Camera Roll/idea.jpg", "13255425825@163.com",
                mailSender, true);
        Assert.assertEquals("成功！！！","发送成功",result);
    }
    @Test
    public void testSendFreeMarkerMail() {
        MailUtils utils = new MailUtils();
        String result = utils.sendMailByFreeMarker( "albert.cui再次来信",
                "C:/Users/lyCui/Pictures/Camera Roll/idea.jpg", "13255425825@163.com",
                mailSender, configuration);
        Assert.assertEquals("成功！！！","发送成功",result);
    }
    @Test
    public void testSendVelocityMail() {
        MailUtils utils = new MailUtils();
        String result = utils.sendMailByVelocity( "albert.cui再次来信",
                "C:/Users/lyCui/Pictures/Camera Roll/idea.jpg", "13255425825@163.com",
                mailSender, velocityEngine);
        Assert.assertEquals("成功！！！","发送成功",result);
    }
    @Test
    public void testIoc() throws IOException, TemplateException {
        System.out.println(configuration);
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        Configuration configuration = bean.getObject();
        System.out.println(configuration);
        logger.isErrorEnabled();
        logger.info("success");
        logger.debug("success");
        logger.error("success");
    }
}
