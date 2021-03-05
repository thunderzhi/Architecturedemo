package com.cxz.impl;

import com.cxz.util.RedisUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/2 15:23
 */
@Service
public class RedisService1 {
    @Autowired
    public RedisUtil2 redisUtil2;

    public boolean set(String key, Object value){
        return this.redisUtil2.set(  key, value);
    }
}
