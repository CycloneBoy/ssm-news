package com.nowcoder.service;

import com.nowcoder.model.User;

import java.util.Map;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
public interface UserService {

    public Map<String,Object> register(String username,String password);

    public User getUser(int id);
}
