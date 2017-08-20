package com.nowcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CycloneBoy on 2017/7/14.
 */

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String index(){
        return "hello world";
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
