package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index2")
    public String index2() {
        return "index2";
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


}
