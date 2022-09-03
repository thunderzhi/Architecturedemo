package com.cxz.iodemo.basedemo;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 15:51
 */
public class SingleApp1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new SocketHttpClient().start("www.baidu.com", 80);
        }
    }
}
