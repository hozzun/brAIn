package com.ssafy.brAIn.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(String key,String content,Long expireTime) {

        redisTemplate.opsForList().rightPush(key, content);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public void removeDataInList(String key,String content) {
        redisTemplate.opsForList().remove(key, 1, content);
    }

    public List<Object> getListFromKey(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public void setSortedSet(String key, int score, String value) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(key, value, score);
    }

    public List<Object> getSortedSet(String key) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        Set<Object> sortedSet = zSetOps.range(key, 0, -1);
        return new ArrayList<>(sortedSet);
    }


}
