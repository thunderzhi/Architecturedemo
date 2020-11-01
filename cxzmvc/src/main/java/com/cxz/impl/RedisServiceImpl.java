package com.cxz.impl;

import com.cxz.service.RedisService;
import org.springframework.stereotype.Service;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1 22:16
 */
@Service
public class RedisServiceImpl implements RedisService {

    
    @Override
    public boolean set(String key, Object value) {
        return false;
    }
}
