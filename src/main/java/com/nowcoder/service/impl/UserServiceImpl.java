package com.nowcoder.service.impl;

import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;
import com.nowcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    @Override
    public Map<String, Object> register(String username, String password) {
        return null;
    }

    @Override
    public User getUser(int id) {
        return userDAO.selectById(id);
    }
}
