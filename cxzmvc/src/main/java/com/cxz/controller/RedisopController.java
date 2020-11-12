package com.cxz.controller;

import com.cxz.impl.RedisService;
import com.cxz.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/5 16:38
 */
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
    //@ResponseBody
    public Map<String, String> addkey(@RequestParam("key")  String key) throws JsonProcessingException {
        User u = new User();
        HashMap json = new HashMap();
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        u.setAge(dateStr);
        u.setName(key);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonstr = objectMapper.writeValueAsString(u);

        //String key = "cxzmvc"+dateStr;
        //Timestamp time1 = new Timestamp(System.currentTimeMillis());
         boolean b = redisService.setStr(key,u);
        logger.debug("set is executed!"+key);

        json.put("success", String.valueOf(b));
        //json.put("success",key);
        return json;
    }
    /*
     * 添加带有过期时间key*/
    @RequestMapping(value = "/expstr", method = {RequestMethod.POST})
    public Map<String, String> addexpstr(@RequestParam("key")  String key) throws JsonProcessingException {
        User u = new User();
        HashMap json = new HashMap();
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        u.setAge(dateStr);
        u.setName(key);

        boolean b = redisService.setStr(key,u,10);
        logger.debug("addexpstr is executed!"+key);
        json.put("success", String.valueOf(b));

        return json;
    }
    /*
     * 获取key value*/
    @RequestMapping(value = "/getkey", method = {RequestMethod.POST,RequestMethod.GET})
    //@ResponseBody
    public Map<String, String> getkey(@RequestParam("key")  String key) throws JsonProcessingException {
        User u = new User();
        HashMap json = new HashMap();
        String jsonstr = Long.toString(System.currentTimeMillis()/1000L);
        try {
            jsonstr = redisService.redisUtil.getStr(key);
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
    public Map<String, String> incrment(@RequestParam("key")  String key,@RequestParam("isincr")  boolean isincr) throws JsonProcessingException {
        User u = new User();
        HashMap json = new HashMap();
        String jsonstr = Long.toString(System.currentTimeMillis()/1000L);
        long res=0;
        try {
            if (isincr)
            {
                res= redisService.incr(key,1);
            }
            else{
                res= redisService.decr(key,1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("fail", "fail");
        }

        logger.debug("incrment is executed!"+key);
        //json.put("success", String.valueOf(b));
        json.put("success",jsonstr);
        return json;
    }
    /*
     * lock*/
    @RequestMapping(value = "/lock", method = {RequestMethod.POST})
    public Map<String, String> lock(){
        HashMap json = new HashMap();

        boolean res = false;

        res= redisService.lock("lock","1",10000);
        json.put("lock res =",String.valueOf(res));
        return json;
    }
    @RequestMapping(value = "/unlock")
    public Map<String, String> unlock(){
        HashMap json = new HashMap();

        boolean res = false;

        res= redisService.unlock("lock","1");
        json.put("unlock res =",String.valueOf(res));
        return json;
    }


}
