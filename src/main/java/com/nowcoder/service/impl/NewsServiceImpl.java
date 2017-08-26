package com.nowcoder.service.impl;

import com.nowcoder.dao.NewsDAO;
import com.nowcoder.model.News;
import com.nowcoder.service.NewsService;
import com.nowcoder.util.ToutiaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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
        //xxx. = a.jpg
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if(dotPos < 0){
            return  null;
        }
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
        if(!ToutiaoUtil.isFileAllowed(fileExt)){
            return null;
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + "."+fileExt;
        Files.copy(file.getInputStream(),new File(ToutiaoUtil.IMAGE_DIR+fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        return ToutiaoUtil.TOUTIAO_DOMAIN+"image?name=" + fileName;
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
