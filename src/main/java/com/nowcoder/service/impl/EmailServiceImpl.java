package com.nowcoder.service.impl;


import com.nowcoder.service.EmailService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by CycloneBoy on 2017/7/18.
 */
@Service
public class EmailServiceImpl implements EmailService {

    //@Autowired
    private String emailSendFrom = "2368311551@qq.com";

    @Autowired
    private JavaMailSender  mailSender;

    @Autowired
    private SpringTemplateEngine thymeleaf;

    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSendFrom);
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);

    }

    @Override
    public void sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(emailSendFrom);
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);

            for(Pair<String,File> pair : attachments){
                helper.addAttachment(pair.getKey(),new FileSystemResource(pair.getValue()));
            }

            mailSender.send(mimeMessage);
        }catch (Exception e){
            System.out.println("发送带附件邮件出现错误: " + e.toString());
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void sendInlineMail(String sendTo, String title, String html, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(emailSendFrom);
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(html,true);

            for(Pair<String,File> pair : attachments){
                helper.addAttachment(pair.getKey(),new FileSystemResource(pair.getValue()));
            }

            mailSender.send(mimeMessage);
        }catch (Exception e){
            System.out.println("发送带附件的Inline邮件出现错误: " + e.toString());
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void sendTemplateMail(String sendTo, String title,String template,
                                 Map<String, Object> content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(emailSendFrom);
            helper.setTo(sendTo);
            helper.setSubject(title);

            Context ctx = new Context();
            for(Map.Entry<String,Object> entry: content.entrySet()){
                ctx.setVariable(entry.getKey(),entry.getValue());
            }
            String emailText = thymeleaf.process(template,ctx);
            helper.setText(emailText,true);

            mailSender.send(mimeMessage);
        }catch (Exception e){
            System.out.println("发送带模板的邮件出现错误: " + e.toString());
            throw  new RuntimeException(e);
        }finally {

        }
    }
}
