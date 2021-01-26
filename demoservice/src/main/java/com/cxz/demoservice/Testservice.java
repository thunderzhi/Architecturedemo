package com.cxz.demoservice;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/6 12:17
 */
@Service
public class Testservice {

    //通过注解引入配置
    @Resource(name = "defaultThreadPool")
    private ThreadPoolTaskExecutor executor;


    public String test(){
        String ttt="";
        Future<?> submit = executor.submit(new Runnable() {

            @Override
            public void run() {
                String v = "xxxxx";
            }

        });
        return ttt;
    }

}
