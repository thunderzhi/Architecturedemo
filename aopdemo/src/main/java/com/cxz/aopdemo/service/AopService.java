package com.cxz.aopdemo.service;

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

    @Override
    public void test(){
        System.out.println("test test test");
    }
}
