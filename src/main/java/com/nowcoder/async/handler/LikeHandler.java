package com.nowcoder.async.handler;

import com.nowcoder.async.EventHandler;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventType;
import com.nowcoder.model.Message;
import com.nowcoder.model.User;
import com.nowcoder.service.MessageService;
import com.nowcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
@Component
public class LikeHandler implements EventHandler{
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Override
    public void doHandler(EventModel model) {
        Message message = new Message();
        User user = userService.getUser(model.getActorId());
        int toId = model.getEntityOwnerId();
        message.setToId(model.getEntityOwnerId());
        message.setContent("用户" + user.getName() +
                "赞了你的咨询，http://127.0.0.1:8080/news/" +
                String.valueOf(model.getEntityId()));
        // SYSTEM ACCOUNT
        message.setFromId(3);
        message.setCreatedDate(new Date());
        message.setConversationId(3 < toId ? String.format("%d_%d",3,toId) :
                String.format("%d_%d",toId,3));
        messageService.addMessage(message);
        System.out.println("用户" + user.getName() +
                "赞了你的咨询，http://127.0.0.1:8080/news/" +
                String.valueOf(model.getEntityId()));
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
