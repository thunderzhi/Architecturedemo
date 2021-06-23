package com.cxz.streamdemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/16 16:05
 */
public class reducedemo {
    public static void main(String[] args) {

        String a = "Programming";
        String b = new String("Programming");
        String c = "Program" + "ming";

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a.intern() == b.intern());


        //reduce
         //new reducefunc().reducetest();
         //limit skip
        //new limitskipfunc().limitskiptest();

    }
}
