package com.github.pengliangs.email;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEmailApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Test
    public void sendSimpleEmailTest() {
        SimpleMailMessage msg = new SimpleMailMessage();
        //发送人
        msg.setFrom("1427985322@qq.com");
        //收件人
        msg.setTo("2874267468@qq.com");
        //主题
        msg.setSubject("测试邮件发送,sendSimpleEmailTest");
        //主题内容
        msg.setText("https://pengliangs.github.io");
        javaMailSender.send(msg);
    }

    @Test
    public void sendHtmlEmailTest() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("1427985322@qq.com");
        helper.setTo("2440329859@qq.com");
        helper.setSubject("测试邮件发送,sendHtmlEmailTest");
        helper.setText("<div style='width:500px;height:500px;text-align: center;'>" +
                "<h1>我的个人博客</h1>" +
                "<p>https://pengliangs.github.io</p>" +
                "</div>", true);
        javaMailSender.send(message);
    }


    @Test
    public void sendAnnexEmailTest() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("1427985322@qq.com");
        helper.setTo("2440329859@qq.com");
        helper.setSubject("测试带附件的邮件");
        helper.setText("带附件的邮件内容");

        FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/img/gg.jpg"));
        //加入邮件
        helper.addAttachment("图片.jpg", file);
        javaMailSender.send(message);
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException, IOException, TemplateException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("1427985322@qq.com");
        helper.setTo("2440329859@qq.com");
        helper.setSubject("测试模板邮件");

        Map<String, Object> model = new HashMap<>();
        model.put("title", "来自远方的信件");
        model.put("content","这是我的博客：https://pengliangs.github.io");
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email-template.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
        javaMailSender.send(message);
    }
}
