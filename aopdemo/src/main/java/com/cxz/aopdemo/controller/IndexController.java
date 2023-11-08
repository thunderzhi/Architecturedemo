package com.cxz.aopdemo.controller;

import com.cxz.aopdemo.service.AopService;
import com.cxz.aopdemo.service.AopService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/9 16:24
 */
@RestController
public class IndexController {
    @Autowired
    private AopService aopService;
    @Autowired
    private AopService2 aopService2;
    @GetMapping("/index")
    //@DoneTime(param = "IndexController")
    public String index(){
        aopService.test();
        System.out.println("方法执行");
        return "hello ccccc";
    }

    @GetMapping("/index2")
    public String index2(){
        aopService2.test();

        System.out.println("方法2执行");
        return "hello cccccd";
    }
    @GetMapping("/index3")
    public String index3(){
        aopService.test1();

        System.out.println("方法3执行");
        return "hello ttt";
    }
}