package com.cxz.demospringboot.controller;

import com.cxz.demoservice.Testservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/6 12:07
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    public Testservice testservice;

    @RequestMapping("/test")
    public String test(){
        return testservice.test();
    }
}
