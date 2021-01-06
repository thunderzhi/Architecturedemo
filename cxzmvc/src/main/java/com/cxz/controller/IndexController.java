package com.cxz.controller;


import com.cxz.dao.redis.RedisDao;
import com.cxz.impl.redis.RedisDaoImpl;
import com.cxz.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/26 15:08
 */
@RestController
@Api(tags = "Index")
@RequestMapping("/")
public class IndexController {
    private static final Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    public RedisDao redisDao;

    @RequestMapping("/index")
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @RequestMapping("/para")
    public String para() {
        return "index2";
    }

    @RequestMapping("/json")
    //@ResponseBody
    public Map<String, String> json(){
        HashMap json = new HashMap();
        json.put("success", "XXXXXXXXX");
        return json;
    }


    @RequestMapping("/rk")
    //@ResponseBody
    public Map<String, String> Randomkey(){
        HashMap json = new HashMap();
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        String key = "cxzmvc"+dateStr;
        //Timestamp time1 = new Timestamp(System.currentTimeMillis());
        //RedisDaoImpl.redisUtil2.set(key,dateStr);
        json.put("success", key);
        logger.debug("getWelcome is executed!");
        return json;
    }

    @RequestMapping("/getrk")
    //@ResponseBody
    public Map<String, String> GetRandomkey(String k){
        HashMap json = new HashMap();
        //String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        //String key = "cxzmvc"+dateStr;
        //Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Object v = redisDao.getStr(k);
        json.put("success", v);
        return json;
    }

    @RequestMapping("/add")
    //@ResponseBody
    public Map<String, String> adduser(String k) throws JsonProcessingException {
        User u = new User();
        HashMap json = new HashMap();
        String dateStr = Long.toString(System.currentTimeMillis()/1000L);
        u.setAge(dateStr);
        u.setName(k);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonstr = objectMapper.writeValueAsString(u);

        //String key = "cxzmvc"+dateStr;
        //Timestamp time1 = new Timestamp(System.currentTimeMillis());
        boolean b = redisDao.setStr(k,jsonstr);
        logger.debug("add is executed!");
        json.put("success", String.valueOf(b));
        return json;
    }

    @RequestMapping("/get")
    public Map<String, Object> getk(String k) throws IOException {
        HashMap json = new HashMap();

        String jsonstr = "";
        try {
            jsonstr = redisDao.getStr(k);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("fail", "fail");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User obj = objectMapper.readValue(jsonstr,User.class);
        json.put("success", obj);
        return json;
    }

}
