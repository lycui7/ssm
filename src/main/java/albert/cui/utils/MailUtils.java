package albert.cui.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 发送邮件的工具类
 * @author albert.cui
 * @date 2019/2/24 14:22
 */
public class MailUtils {
    /**
     *
     * @param text 发送的内容
     * @param subject 邮件的标题
     * @param location 文件的地址
     * @param emailAdress 目的地
     * @param mailSender 发送邮件的核心类
     * @param type 如果为true,则发送html格式文本
     * @return
     */
    public String sendMail(String text, String subject, String location, String emailAdress,
                           JavaMailSender mailSender,Boolean type) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        Properties prop = new Properties();
        try {
            //从配置文件拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            String from = prop.get("mail.smtp.username")+"";
            messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            //发件人邮箱
            messageHelper.setFrom(from);
            //收件人邮箱
            messageHelper.setTo(emailAdress);
            //邮件的标题
            messageHelper.setSubject(subject);
            //邮件的正文
            if(type) {
                //发送html格式的文本
                messageHelper.setText(text,true);
            }else {
                //发送普通文本
                messageHelper.setText(text, false);
            }
            //附件文件路径
            String filename = StringUtils.getFilename(location);
            //定义发送的资源位置
            File file = new File(location);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            messageHelper.addAttachment(filename,fileSystemResource);
            //开始发送邮件
            mailSender.send(mimeMessage);
        }catch (MessagingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return  "发送成功";
    }

    /**
     * FreeMarker模板格式的邮件发送
     * @param subject
     * @param location
     * @param emailAdress
     * @param mailSender
     * @param freeMarkerConfiguration freemarker配置管理类
     * @return
     */
    public String sendMailByFreeMarker(String subject, String location, String emailAdress,
                                       JavaMailSender mailSender, Configuration freeMarkerConfiguration) {
        MimeMessage mMessage = mailSender.createMimeMessage();
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        try {
            // 从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            String from = prop.get("mail.smtp.username") + "";
            mMessageHelper = new MimeMessageHelper(mMessage, true);
            // 发件人邮箱
            mMessageHelper.setFrom(from);
            // 收件人邮箱
            mMessageHelper.setTo(emailAdress);
            // 邮件的主题也就是邮件的标题
            mMessageHelper.setSubject(subject);
            // 解析模板文件
            mMessageHelper.setText(getText(freeMarkerConfiguration), true);
            // 通过文件路径获取文件名字
            String filename = StringUtils.getFilename(location);
            // 定义要发送的资源位置
            File file = new File(location);
            FileSystemResource resource = new FileSystemResource(file);
            //添加附件
            mMessageHelper.addAttachment(filename, resource);
            //发送邮件
            mailSender.send(mMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败！！！";
        } catch (IOException e) {
            e.printStackTrace();
            return "发送失败！！！";
        } catch (TemplateException e) {
            e.printStackTrace();
            return "发送失败！！！";
        }
        return "发送成功";
    }

    /**
     * Velocity模板格式邮件发送
     * @param subject
     * @param location
     * @param emailAdress
     * @param javaMailSender
     * @param velocityEngine velocity模板引擎
     * @return
     */
    public String sendMailByVelocity(String subject, String location, String emailAdress, JavaMailSender javaMailSender,
                                     VelocityEngine velocityEngine) {
        MimeMessage mMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        try {
            // 从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            String from = prop.get("mail.smtp.username") + "";
            mMessageHelper = new MimeMessageHelper(mMessage, true, "UTF-8");
            // 发件人邮箱
            mMessageHelper.setFrom(from);
            // 收件人邮箱
            mMessageHelper.setTo(emailAdress);
            // 邮件的主题也就是邮件的标题
            mMessageHelper.setSubject(subject);
            Map<String, Object> map = new HashMap<>();
            // 获取日期并格式化
            Date date = new Date();
            DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
            String str = bf.format(date);
            map.put("date", str);
            String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email.vm", "UTF-8", map);
            System.out.println(content);
            mMessageHelper.setText(content, true);
            // 通过文件路径获取文件名字
            String filename = StringUtils.getFilename(location);
            // 定义要发送的资源位置
            File file = new File(location);
            FileSystemResource resource = new FileSystemResource(file);
            mMessageHelper.addAttachment(filename, resource);
            //开始发送邮件
            javaMailSender.send(mMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return  "发送失败";
        } catch (IOException e) {
            e.printStackTrace();
            return  "发送失败";
        }
     return  "发送成功";
    }

    /**
     *读取freemarker模板
     * @param freeMarkerConfiguration
     * @return
     */
    private String getText(Configuration freeMarkerConfiguration) throws IOException, TemplateException {
        String text = "";
            Template template = freeMarkerConfiguration.getTemplate("email.ftl");
            //通过map传递动态数据
            Map<String,Object> map = new HashMap<>();
            map.put("user","albert.cui");
            //解析模板文件
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
            System.out.println("getText()->>>>>>>>>");
            System.out.println(text);
        return text;
    }
}
