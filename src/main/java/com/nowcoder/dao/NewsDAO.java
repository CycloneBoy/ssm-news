package com.nowcoder.dao;

import com.nowcoder.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/19.
 */
@Mapper
public interface NewsDAO {
    String TABLE_NAME = "news";
    String INSERT_FIELDS =" title, link, image, like_count, comment_count, create_date, user_id ";
    String SELECT_FIELDS =" id, "+ INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME , "(",INSERT_FIELDS ,
            ") values (#{title},#{link},#{image},#{likeCount},#{commentCount},#{createDate},#{userId})"})
    int addNews(News news);

    @Select({"select " , SELECT_FIELDS, " from", TABLE_NAME , " where id=#{id}"})
    News getById(int id);

    @Update({"update ", TABLE_NAME," set comment_count = #{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

    @Update({"update ", TABLE_NAME," set like_count = #{likeCount} where id=#{id}"})
    int updateLikeCount(@Param("id") int id, @Param("likeCount") int likeCount);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME,
            "where user_id=#{userId} order by create_date desc limit #{offset}, " +
            "#{limit}" })
    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME,
            " order by create_date desc limit #{offset}, " +
                    "#{limit}" })
    List<News> selectNewsByOffset( @Param("offset") int offset, @Param("limit") int
            limit);
}
