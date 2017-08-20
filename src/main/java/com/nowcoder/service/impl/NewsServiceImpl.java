package com.nowcoder.service.impl;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;
import com.nowcoder.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    public List<News> getLatestNews(int userid, int offset, int limit) {
        return newsDAO.selectByUserIdAndOffset(userid,offset,limit);
    }

    @Override
    public int addNews(News news) {
        newsDAO.addNews(news);
        return news.getId();
    }

    @Override
    public News getById(int newsId) {
        return newsDAO.getById(newsId);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public int updateCommentCount(int id, int count) {
        return newsDAO.updateCommentCount(id,count);
    }

    @Override
    public int updateLikeCount(int id, int count) {
        return newsDAO.updateLikeCount(id,count);
    }
}
