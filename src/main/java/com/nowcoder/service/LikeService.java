package com.nowcoder.service;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
public interface LikeService {

    public int getLikeStatus(int userId,int entityType,int entityId);

    public long like(int userId,int entityType,int entityId);

    public long disLike(int userId,int entityType,int entityId);
}
