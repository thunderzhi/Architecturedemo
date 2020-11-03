package com.cxz.redisdemo;

import com.cxz.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisdemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RedisUtil redisUtil;
    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key", "redis_vale1111");
    }
    @Test
    public void set2() {
        redisUtils.set("redis_key_time", "1111",10000);
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtils.get("redis_key");
        System.out.println(value);
    }
    @Test
    public void getexpiretime(){
        String key ="redis_key_time";
        redisUtils.set(key, "1111",20000);
        long time = redisUtil.getExpire(key);
        System.out.println(time);

    }

    @Test
    public void setlock(){
        boolean res = redisUtil.lock("lock1","1111111",120);
        String str = res?" set successed":"failed";
        System.out.println(" lock  "+ str);

    }

    @Test
    public void releaseLock(){
        boolean res = redisUtil.releaseLock("lock1","1111111");
        String str = res?" set successed":"failed";
        System.out.println(" releaseLock  "+ str);

    }
}
