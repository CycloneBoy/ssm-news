package com.nowcoder.dao;

import com.nowcoder.SsmTestApplication;
import com.nowcoder.model.News;
import com.sun.xml.internal.rngom.binary.DataExceptPattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SsmTestApplication.class)
public class NewsDAOTest {

    @Autowired
    private NewsDAO newsDAO;

    @Test
    public void addNews() throws Exception {
        Random random = new Random();
        for(int i=0;i <11;i++){
            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000*3600*5*i);
            news.setCreateDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i+1);
            news.setUserId(i+1);
            news.setTitle(String.format("TITLE{%d}",i));
            news.setLink(String.format("http://www.nowcoder.com/%d.html", i));
            newsDAO.addNews(news);
        }
    }

    @Test
    public void getById() throws Exception {
        int id =3;
        News news = newsDAO.getById(id);
        int result = newsDAO.updateCommentCount(id,10);
        System.out.println("更新评论数: " + result);
        result = newsDAO.updateLikeCount(id,10);
        System.out.println("更新点赞数: " + result);

        List<News> list = newsDAO.selectByUserIdAndOffset(id,0,10);
        System.out.println(list.size() + " "+ list.toString());
    }

    @Test
    public void updateCommentCount() throws Exception {
    }

    @Test
    public void updateLikeCount() throws Exception {
    }

    @Test
    public void selectByUserIdAndOffset() throws Exception {
    }

}