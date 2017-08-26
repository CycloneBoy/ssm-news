package com.nowcoder.dao;

import com.nowcoder.SsmTestApplication;
import com.nowcoder.model.Comment;
import com.nowcoder.model.EntityType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by CycloneBoy on 2017/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SsmTestApplication.class)
public class CommentDAOTest {

    @Autowired
    private CommentDAO commentDAO;

    @Test
    public void addComment() throws Exception {
        for(int i=0;i<11;i++){

            for(int j = 0; j < 3;j++){
                Comment comment = new Comment();
                comment.setUserId( i +1);
                comment.setCreatedDate(new Date());
                comment.setStatus(0);
                comment.setContent("这里是一个评论啊！" + String.valueOf(j));
                comment.setEntityId(12+i);
                comment.setEntityType(EntityType.ENTITY_NEWS);
                commentDAO.addComment(comment);
            }

        }
    }

    @Test
    public void selectByEntity() throws Exception {
        List<Comment> comments = commentDAO.selectByEntity(13,EntityType.ENTITY_NEWS);

        for(Comment comment : comments){
            System.out.println(comment.toString());
        }
    }

    @Test
    public void getCommentCount() throws Exception {
        System.out.println(commentDAO.getCommentCount(13,EntityType.ENTITY_NEWS));
    }

}