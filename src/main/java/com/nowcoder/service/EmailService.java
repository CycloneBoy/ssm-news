package com.nowcoder.service;

import javafx.util.Pair;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by CycloneBoy on 2017/7/18.
 */
public interface EmailService {

    /**
     * 发送简单邮件
     * @param sendTo
     * @param title
     * @param content
     */
    public void sendSimpleMail(String sendTo, String title, String content);

    /**
     * 发送带附件的简单邮件
     * @param sendTo
     * @param title
     * @param content
     * @param attachments
     */
    public void sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments);

    /**
     * 发送带附件的Inline邮件
     * @param sendTo
     * @param title
     * @param html
     * @param attachments
     */
    public void sendInlineMail(String sendTo, String title, String html, List<Pair<String, File>> attachments);
    /**
     * 发送模板邮件带附件
     * @param sendTo
     * @param title
     * @param content
     * @param attachments
     */
    public void sendTemplateMail(String sendTo, String title, Map<String, Object> content, List<Pair<String, File>>
            attachments);
}
