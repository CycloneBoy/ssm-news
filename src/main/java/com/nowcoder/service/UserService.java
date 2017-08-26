package com.nowcoder.service;

import com.nowcoder.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
public interface UserService {

    public Map<String,Object> register(String username,String password);

    public Map<String,Object> login(String username,String password);

    public String addLoginTicket(int userId);

    public User getUser(int id);

    public void logout(String ticket);


}
