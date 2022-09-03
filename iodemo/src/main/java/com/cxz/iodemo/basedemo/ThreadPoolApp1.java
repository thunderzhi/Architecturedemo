package com.cxz.iodemo.basedemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 15:57
 */
public class ThreadPoolApp1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 5; i++) {
            System.out.println("i: "+i);
            Thread t = new Thread(()->{
                new SocketHttpClient().start("www.baidu.com", 80);
            });
            executorService.submit(t);
        }
        System.out.println("main is finish  " );
        executorService.shutdown();
    }
}
