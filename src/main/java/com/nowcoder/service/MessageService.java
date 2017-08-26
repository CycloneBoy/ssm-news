package com.nowcoder.service;

import com.nowcoder.model.Message;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/26.
 */
public interface MessageService {

    public int addMessage(Message message);

    public List<Message> getConversationList(int userId,int offset,int limit);

    public List<Message> getConversationDeatil(String conversationId,int offset,int limit);

    public int getUnreadCount(int userId,String conversationId);

    public int deleteMessage(int hasRead,int id);

    public Message getMessageById( int id);
}
