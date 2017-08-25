package com.nowcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;

/**
 * Created by CycloneBoy on 2017/7/14.
 */

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/test")
    public String index(HttpServletRequest request,
                        HttpServletResponse response,
                        HttpSession session){
        String path = request.getContextPath();
        System.out.println(path);
        URL url = this.getClass().getClassLoader().getResource("/temp/test.py");
        System.out.println(url.getPath());
        return "hello world ！！！！path: "+ path + "  url.path: " + url.getPath();
    }

    @RequestMapping("/h")
    public String home(){
        return "this is user !";
    }

   @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("host","https://github.com/CycloneBoy");
        return "index";
   }

   @RequestMapping("/helloerror")
   public String helloError() throws Exception{
        logger.info("/helloerror : some error");
        throw new Exception("发生错误1:helloerror");
   }



   @RequestMapping("/login")
    public String login(){
        return "login";
   }

   @RequestMapping("/ws")
    public String getWs(){
        return "ws";
   }


}
