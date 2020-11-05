package com.cxz.impl;

import com.cxz.util.RedisUtil;
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
public class RedisService {
    @Autowired
    public RedisUtil redisUtil;

    public boolean set(String key, Object value){
        return this.redisUtil.set(  key, value);
    }
}
