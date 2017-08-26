package com.nowcoder.service.impl;

import com.nowcoder.dao.MessageDAO;
import com.nowcoder.model.Message;
import com.nowcoder.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/26.
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageDAO messageDAO;


    @Override
    public int addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    @Override
    public List<Message> getConversationList(int userId, int offset, int limit) {
        return messageDAO.getConversationList(userId,offset,limit);
    }

    @Override
    public List<Message> getConversationDeatil(String conversationId, int offset, int limit) {
        return messageDAO.getConversationDetail(conversationId,offset,limit);
    }

    @Override
    public int getUnreadCount(int userId,String conversationId) {
        return messageDAO.getConversationUnReadCount(userId,conversationId);
    }

    @Override
    public int deleteMessage(int hasRead, int id) {
        return messageDAO.updateMessageHasRead(hasRead,id);
    }

    @Override
    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }
}
