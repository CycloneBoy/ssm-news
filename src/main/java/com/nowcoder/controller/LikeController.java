package com.nowcoder.controller;

import com.nowcoder.model.EntityType;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.News;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.NewsService;
import com.nowcoder.util.ToutiaoUtil;
import com.sun.corba.se.pept.transport.ReaderThread;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = {"/like"},method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    public String like(@Param("newsId") int newsId){
        long likeCount = likeService.like(hostHolder.getUser().getId(),
                 EntityType.ENTITY_NEWS,newsId);
        System.out.println("like likecount " + likeCount);
        //更新喜欢数
        //News news = newsService.getById(newsId);
        newsService.updateLikeCount(newsId,(int)likeCount);

        //return ToutiaoUtil.getJSONString(0,String.valueOf(likeCount));
        return  "redirect:/";
    }

    @RequestMapping(value = {"/dislike"},method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    public String dislike(@Param("newsId") int newsId){
        long likeCount = likeService.disLike(hostHolder.getUser().getId(), EntityType.ENTITY_NEWS,newsId);
        //更新喜欢数
        newsService.updateLikeCount(newsId,(int)likeCount);

        //return ToutiaoUtil.getJSONString(0,String.valueOf(likeCount));
        return  "redirect:/";
    }
}
