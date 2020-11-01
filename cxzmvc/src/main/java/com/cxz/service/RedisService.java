package com.cxz.service;


import com.cxz.util.RedisUtil;
import com.cxz.util.RedisUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/30 16:19
 */

public interface RedisService {

//    @Autowired
//    public RedisUtil2 redisUtil2;

    boolean set(String key, Object value);
}
