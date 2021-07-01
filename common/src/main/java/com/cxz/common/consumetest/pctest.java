package com.cxz.common.consumetest;


import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/7/1 11:25
 */
public class pctest {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Object lock = new Object();
        p1 p1 = new p1(lock, pq);
        c1 c1 = new c1(lock, pq);
        c2 c2 = new c2(lock, pq);
        c3 c3 = new c3(lock, pq);

        ExecutorService service = Executors.newCachedThreadPool();
        Thread t1 = new Thread(p1,"A");
        Thread t2 = new Thread(c1,"B");
        Thread t3 = new Thread(c2,"C");
        Thread t4 = new Thread(c3,"D");
        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);

        service.shutdown();;
    }
}
