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
        //reduce
         //new reducefunc().reducetest();
         //limit skip
        new limitskipfunc().limitskiptest();

    }
}
