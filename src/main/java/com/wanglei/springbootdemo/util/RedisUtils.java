package com.wanglei.springbootdemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 永久有效
     */
    public boolean rightPushAll(final String key,List list){
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key,list);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 取list
     */
    public String leftPopList(final String key){
        String value = null;
        try {
            value = (String)redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 取list
     */
    public String rightPopList(final String key){
        String value = null;
        try {
            value = (String)redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 删除缓存
     */
    public boolean pub(final String channel,String message) {
        boolean result = false;
        try {
            redisTemplate.convertAndSend(channel,message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
