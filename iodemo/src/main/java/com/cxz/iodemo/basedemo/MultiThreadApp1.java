package com.cxz.iodemo.basedemo;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 15:53
 */
public class MultiThreadApp1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("i: "+i);
            Thread t = new Thread(()->{
                new SocketHttpClient().start("www.baidu.com", 80);
            });
            t.start();
        }
        System.out.println("main is finish  " );
    }
}
