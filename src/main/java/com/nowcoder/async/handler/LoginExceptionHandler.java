package com.nowcoder.async.handler;

import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;
import com.nowcoder.model.Message;
import com.nowcoder.service.EmailService;
import com.nowcoder.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
@Component
public class LoginExceptionHandler implements EventHandler{
    @Autowired
    private MessageService messageService;

    @Autowired
    private EmailService emailService;

    @Override
    public void doHandler(EventModel model) {
        Message message = new Message();
        int toId = model.getActorId();
        message.setToId(model.getActorId());
        message.setContent("你上次的登录IP异常");
        // SYSTEM ACCOUNT
        message.setFromId(3);
        message.setCreatedDate(new Date());
        message.setConversationId(3 < toId ? String.format("%d_%d",3,toId) :
                String.format("%d_%d",toId,3));
        messageService.addMessage(message);
        System.out.println("你上次的登录IP异常");

        emailService.sendSimpleMail("534634799@qq.com","头条","你上次的登录IP异常");
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
