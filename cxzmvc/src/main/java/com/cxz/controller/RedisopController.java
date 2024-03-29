package com.cxz.controller;

import com.cxz.impl.redis.RedisDaoImpl;
import com.cxz.model.User;
import com.cxz.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/5 16:38
 */
@Api(tags = "Redisop")
@RestController
@RequestMapping("/redis")
public class RedisopController {
    private static final Logger logger = Logger.getLogger(RedisopController.class);
    @Autowired
    public RedisService redisService;

    @RequestMapping("/index")
    public ModelAndView index2(Model model) {
        model.addAttribute("msg","Hello Spring MVC!");
        return new ModelAndView("redis/redisindex");
    }
    /*
     * 添加key*/
    @RequestMapping(value = "/set", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "个人信息")//swagger 当前接口注解
    //@ResponseBody
    public Map<String, String> addkey(@RequestParam("key")  String key)   {
        HashMap json = new HashMap();
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        //String key = "cxzmvc"+dateStr;
        //Timestamp time1 = new Timestamp(System.currentTimeMillis());
        boolean b = redisService.addkey(key);
        json.put("success", String.valueOf(b));
        return json;
    }

    /*
     * 添加带有过期时间key*/
    @RequestMapping(value = "/expstr", method = {RequestMethod.POST})
    public Map<String, String> addexpstr(@RequestParam("key")  String key)   {
        HashMap json = new HashMap();
        User u = new User();
        u.setName(key);
        boolean b = redisService.addexpstr(key,u);
        json.put("success", String.valueOf(b));
        return json;
    }

    /*
     * 获取key value*/
    @RequestMapping(value = "/getkey", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(httpMethod = "GET", value = "getkey")
    //@ResponseBody
    public Map<String, String> getkey(@RequestParam("key")  String key)  {
        User u = new User();
        HashMap json = new HashMap();
        String jsonstr ="";
        try {
            jsonstr = redisService.getkey(key);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("fail", "fail");
        }
        logger.debug("get is executed!"+key);
        //json.put("success", String.valueOf(b));
        json.put("success",jsonstr);
        return json;
    }

    /*
     * 原子加减key*/
    @RequestMapping(value = "/incrment", method = {RequestMethod.POST})
    //@ResponseBody
    public Map<String, String> incrment(@RequestParam("key")  String key,@RequestParam("isincr")  boolean isincr)
             {
        HashMap json = new HashMap();
        String jsonstr = Long.toString(System.currentTimeMillis()/1000L);
        long res=0;
        try {
            res = redisService.incrment(key,isincr,1);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("fail", "fail");
        }
        json.put("success",jsonstr);
        return json;
    }
    /*
     * lock*/
    @RequestMapping(value = "/lock2")
    @ApiOperation(httpMethod = "GET", value = "lock2")
    public Map<String, String> lock2(){
        HashMap json = new HashMap();
        boolean res = false;
        res= redisService.lock("lock","1",20000);
        json.put("lock res =",String.valueOf(res));
        return json;
    }

    @RequestMapping(value = "/lock")
    @ApiOperation(httpMethod = "GET", value = "lock")
    public Map<String, String> lock(){
        HashMap json = new HashMap();
        boolean res = false;
        res= redisService.lock("lock",Thread.currentThread().getName(),20000);
        json.put("lock res =",String.valueOf(res));
        return json;
    }

    @RequestMapping(value = "/unlock")
    @ApiOperation(httpMethod = "GET", value = "unlock")
    public Map<String, String> unlock(@RequestParam("key")  String v){
        HashMap json = new HashMap();
        boolean res = false;
        res= redisService.unlock("lock",v);
        json.put("unlock res =",String.valueOf(res));
        return json;
    }

    @RequestMapping(value = "/getexp")
    public Map<String, String> getexp(){
        HashMap json = new HashMap();
        long res = 0;
        res= redisService.exp("lock");
        json.put("res =",String.valueOf(res));
        return json;
    }

    @RequestMapping(value = "/addhash")
    public Map<String, String> addhash(String k){
        HashMap json = new HashMap();
        User user = new User();
        user.setAge(Long.toString(System.currentTimeMillis()/1000L));
        user.setName("TTTTT");

        long res = 0;
        res= redisService.exp("lock");
        json.put("res =",String.valueOf(res));
        return json;
    }


}
