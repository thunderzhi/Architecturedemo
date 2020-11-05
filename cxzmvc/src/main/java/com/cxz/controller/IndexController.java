package com.cxz.controller;

import com.cxz.impl.RedisService;
import com.cxz.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/home")
public class IndexController {
    private static final Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    public RedisService redisService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index2")
    public ModelAndView index2(Model model) {
        model.addAttribute("msg","Hello Spring MVC!");

        return new ModelAndView("index2");
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
        //redisService.redisUtil2.set(key,dateStr);
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
        Object v = redisService.redisUtil.get(k);
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
        boolean b = redisService.redisUtil.setStr(k,jsonstr);
        logger.debug("add is executed!");
        json.put("success", String.valueOf(b));
        return json;
    }

    @RequestMapping("/get")
    public Map<String, Object> getk(String k) throws IOException {
        HashMap json = new HashMap();

        String jsonstr = "";
        try {
            jsonstr = redisService.redisUtil.getStr(k);
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
