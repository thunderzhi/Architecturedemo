package com.cxz.impl.redis;

import com.cxz.dao.redis.RedisDao;
import com.cxz.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/13 10:37
 */
@Component
public class RedisDaoImpl implements RedisDao {
    @Autowired
    public RedisUtil redisUtil;

    @Override
    public boolean set(String key, Object value){
        return this.redisUtil.set(  key, value);
    }
    /*设置字符串kv*/
    @Override
    public boolean setStr(String key, Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonstr = objectMapper.writeValueAsString(value);
        return this.redisUtil.setStr(  key, jsonstr);
    }

    /*设置字符串kv,超时时间*/
    @Override
    public boolean setStr(String key, Object value, long time) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonstr = objectMapper.writeValueAsString(value);
        return this.redisUtil.setStr(  key, jsonstr,time);
    }

    /*设置原子自增减*/
    @Override
    public long incr(String key, long delta){
        return this.redisUtil.incr(key, delta);
    }

    /*设置原子自增减*/
    @Override
    public long decr(String key, long delta){
        return this.redisUtil.decr(key, delta);
    }

    /*锁*/
    @Override
    public boolean lock(String key, String value, long milliseconds){
        return this.redisUtil.lock2(key, value,milliseconds);
    }
    @Override
    public boolean unlock(String key, String value){
        return this.redisUtil.releaselock2(key, value);
    }
    @Override
    public long exp(String key){
        return this.redisUtil.getStrExpire(key);
    }
    @Override
    public String getStr(String key) {
        return this.redisUtil.getStr(key);
    }
}
