package com.nowcoder.controller;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.dto.ViewResult;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.News;
import com.nowcoder.model.User;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import com.nowcoder.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by CycloneBoy on 2017/7/20.
 */
@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private NewsService newsService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    private List<ViewObject> getNews(int userId, int offset, int limit){
        List<News> newsList = newsService.getLatestNews(userId,offset,limit);
        int localUserId = hostHolder.getUser() != null ? hostHolder.getUser().getId() : 0;
        List<ViewObject> vos = new ArrayList<>();
        for(News news : newsList){
            ViewObject vo = new ViewObject();
            vo.set("news",news);
            vo.set("user",userService.getUser(news.getUserId()));
            if(localUserId != 0){
                vo.set("like",news.getLikeCount());
            }else {
                vo.set("like",0);
            }
            vos.add(vo);
        }
        return vos;
    }

    private List<ViewResult> getNewsDto(int userId, int offset, int limit){
        List<News> newsList = newsService.getLatestNews(userId,offset,limit);
        int localUserId = hostHolder.getUser() != null ? hostHolder.getUser().getId() : 0;
        List<ViewResult> vos = new ArrayList<>();
        for(News news : newsList){
            ViewResult vo = new ViewResult();
            vo.setNews(news);
            vo.setUser(userService.getUser(news.getUserId()));
            if(localUserId != 0){
                vo.setLike(news.getLikeCount());
            }else {
                vo.setLike(0);
            }
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(value = {"/","/index"},method = {RequestMethod.GET,RequestMethod.POST})
    public String index(Model model,
                        @RequestParam(value = "pop",defaultValue = "0") int pop){
        model.addAttribute("vos",getNewsDto(3,0,10));
        if(hostHolder.getUser() != null){
            pop = 0;
        }
        model.addAttribute("pop",pop);
        return "home";
    }

    @RequestMapping(path = {"/user/{userId}"},method = {RequestMethod.GET,RequestMethod.GET})
    public String userIndex(Model model, @PathVariable("userId") int userId){
        model.addAttribute("vos",getNews(userId,0,10));
        return "home";
    }

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public ModelAndView home(ModelMap modelMap){

        logger.info("url=\"home ...回到首页...");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        modelMap.addAttribute("name","CycloneBoy");
        modelMap.addAttribute("today",sdf.format(date));
        User user = new User();
        user.setId(3);
        user.setName("test");
        user.setPassword("1234");
        modelMap.addAttribute("user",user);
        return new ModelAndView("index");
    }

}
