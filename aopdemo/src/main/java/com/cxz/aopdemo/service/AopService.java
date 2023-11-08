package com.cxz.aopdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/9 16:24
 */
@Service
public class AopService implements Test{
    @Autowired
    private AopService aopService;
    @Override
    public void test(){
        System.out.println("test test test");
    }

    public void test1(){
        System.out.println("test1 test1 test1");
        aopService.test();
    }
}
