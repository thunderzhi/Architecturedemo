package com.cxz.service;


import com.cxz.dao.redis.RedisDao;
import com.cxz.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/2 15:23
 */
@Service
public class RedisService {

    private static final Logger logger = Logger.getLogger(RedisService.class);

    @Autowired
    RedisDao redisDao;

    public boolean addkey(String key) throws JsonProcessingException {
        User u = new User();
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        u.setAge(dateStr);
        u.setName(key);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonstr = objectMapper.writeValueAsString(u);
        boolean res = redisDao.setStr(key,u);
        logger.debug("addkey set is executed!"+key);
        return res;
    }

    public boolean addexpstr(String key,User u) throws JsonProcessingException {
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        u.setAge(dateStr);
        boolean res = redisDao.setStr(key,u,10);
        logger.debug("addexpstr is executed!"+key);
        return res;
    }

    public String getkey(String key){
        String jsonstr = Long.toString(System.currentTimeMillis()/1000L);
        jsonstr = redisDao.getStr(key);
        return  jsonstr;
    }

    public long incrment(String key ,boolean isincr,int step){
        long res = 0;
        if (isincr)
        {
            res= redisDao.incr(key,1);
        }
        else{
            res = redisDao.decr(key,1);
        }
        logger.debug("incrment is executed!"+key);
        return res;
    }

    public boolean lock(String k ,String v ,long expire){
        return redisDao.lock(k,v,expire);
    }
    public boolean unlock(String k ,String v){
        return redisDao.unlock(k,v);
    }

    public long exp(String k){
        return redisDao.exp(k);
    }
}
