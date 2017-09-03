package com.nowcoder.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
@Service
public class JedisAdapter implements InitializingBean{
    private static  final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    private Jedis jedis = null;
    private JedisPool pool = null;

    public static void print(int index,Object obj){
        System.out.println(String.format("%d,%s",index,obj.toString()));
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.flushAll();
        // get,set
        jedis.set("hello","world");
        print(1,jedis.get("hello"));
        jedis.rename("hello","newhello");
        print(1,jedis.get("newhello"));
        jedis.setex("hello2",15,"world");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("127.0.0.1",6379);
    }

    private Jedis getJedis(){
        return pool.getResource();
    }

    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return getJedis().get(key);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return  null;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public void set(String key,String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public long sadd(String key,String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sadd(key,value);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return 0;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public long srem(String key ,String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.srem(key,value);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return 0;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public boolean sismember(String key,String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return  false;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public long scard(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return 0;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public void setex(String key,String value){
        // 验证码, 防机器注册，记录上次注册时间，有效期3天
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.setex(key,10,value);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public long lpush(String key,String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            System.out.println("lpush : " + key + " " + value);
            return jedis.lpush(key,value);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return  0;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public List<String> brpop(int timeout,String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            System.out.println("brpop : " + key );
            return jedis.brpop(timeout,key);
        }catch (Exception e){
            logger.error("发生异常" + e.getMessage());
            return  null;
        }finally {
            if(jedis!= null){
                jedis.close();
            }
        }
    }

    public void setObject(String key,Object obj){
        set(key, JSON.toJSONString(obj));
    }

    public <T> T getObject(String key,Class<T> clazz){
        String value = get(key);
        if(value != null){
            return  JSON.parseObject(value,clazz);
        }
        return  null;
    }
}
