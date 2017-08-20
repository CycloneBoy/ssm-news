package com.nowcoder.dao;

import com.nowcoder.SsmTestApplication;
import com.nowcoder.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SsmTestApplication.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;
    @Test
    public void addUser() throws Exception {
        Random random = new Random();
        for(int i=0;i<11;i++){
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d",i));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);
        }
    }

    @Test
    public void selectById() throws Exception {
        User user = userDAO.selectById(3);
        System.out.println(user);
        user.setPassword("123456");
        userDAO.updatePassword(user);

        user = userDAO.selectByName("USER5");
        System.out.println(user);

        userDAO.deleteById(10);
    }

    @Test
    public void selectByName() throws Exception {
    }

    @Test
    public void updatePassword() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

}