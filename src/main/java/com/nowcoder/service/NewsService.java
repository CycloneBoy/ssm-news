package com.nowcoder.service;

import com.nowcoder.model.News;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/20.
 */
public interface NewsService {

    public List<News> getLatestNews(int userid,int offset,int limit);

    public int addNews(News news);

    public News getById(int newsId);

    public String saveImage(MultipartFile file ) throws IOException;

    public int updateCommentCount(int id,int count);

    public int updateLikeCount(int id,int count);
}
