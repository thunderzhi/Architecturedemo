package com.cxz.dao.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/13 10:36
 */
@Repository
public interface RedisDao {
    boolean set(String key, Object value);

    /*设置字符串kv*/
    boolean setStr(String key, Object value) throws JsonProcessingException;

    /*设置字符串kv,超时时间*/
    boolean setStr(String key, Object value, long time) throws JsonProcessingException;

    /*设置原子自增减*/
    long incr(String key, long delta);

    /*设置原子自增减*/
    long decr(String key, long delta);

    /*锁*/
    boolean lock(String key, String value, long milliseconds);

    boolean unlock(String key, String value);

    long exp(String key);

    String getStr(String key);


}
