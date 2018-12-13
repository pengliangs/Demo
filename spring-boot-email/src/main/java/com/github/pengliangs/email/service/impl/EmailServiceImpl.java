package com.github.pengliangs.email.service.impl;

import com.github.pengliangs.email.dto.SendEmailDto;
import com.github.pengliangs.email.service.EmailService;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * @author pengliang on 2018-12-13 10:45
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public boolean send(SendEmailDto sendEmailDto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(sendEmailDto.getTo().split(","));
            helper.setSubject(sendEmailDto.getTitle());
            if (!sendEmailDto.getFile().isEmpty()) {
                helper.addAttachment("图片.jpg", sendEmailDto.getFile());
            }
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email-template.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, sendEmailDto);
            helper.setText(html, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
