package com.cxz.controller;

import com.cxz.service.RedisService;
import com.cxz.util.RedisUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Timestamp;
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
        redisService.redisUtil2.set(key,dateStr);
        json.put("success", key);
        return json;
    }
}
