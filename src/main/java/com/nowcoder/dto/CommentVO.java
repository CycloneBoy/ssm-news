package com.nowcoder.dto;

import com.nowcoder.model.Comment;
import com.nowcoder.model.User;

/**
 * Created by CycloneBoy on 2017/8/26.
 */
public class CommentVO {
    private User user;
    private Comment comment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
