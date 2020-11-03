package com.cxz.controller;

import com.cxz.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/2 11:05
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    public TestService testService;
    @RequestMapping("/gettest")
    public String gettest(){
        return testService.test();
    }
}