package com.nowcoder.service.impl;

import com.nowcoder.service.LikeService;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public int getLikeStatus(int userId, int entityType, int entityId) {
       String likeKey = RedisKeyUtil.getLikeKey(entityId,entityType);
       if(jedisAdapter.sismember(likeKey,String.valueOf(userId))){
           return  1;
       }
       String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId,entityType);
       return  jedisAdapter.sismember(disLikeKey,String.valueOf(userId)) ? -1 : 0;
    }

    @Override
    public long like(int userId, int entityType, int entityId) {
       //在喜欢集合里增加
        String likeKey = RedisKeyUtil.getLikeKey(entityId,entityType);
        jedisAdapter.sadd(likeKey,String.valueOf(userId));
        System.out.println("service like " + likeKey + " " + userId );
        //从反对里删除
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId,entityType);
        jedisAdapter.srem(disLikeKey,String.valueOf(userId));

        System.out.println("service like " + likeKey + " " + jedisAdapter.scard(likeKey));
        return jedisAdapter.scard(likeKey);
    }

    @Override
    public long disLike(int userId, int entityType, int entityId) {
        //在反对集合里增加
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId,entityType);
        jedisAdapter.sadd(disLikeKey,String.valueOf(userId));

        //从喜欢里删除
        String likeKey = RedisKeyUtil.getLikeKey(entityId,entityType);
        jedisAdapter.srem(likeKey,String.valueOf(userId));
        return jedisAdapter.scard(likeKey);
    }
}
