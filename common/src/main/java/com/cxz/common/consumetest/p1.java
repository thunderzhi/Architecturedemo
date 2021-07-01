package com.cxz.common.consumetest;


import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/7/1 10:47
 */
public class p1 implements Runnable {

    private Object lock;

    private PriorityQueue<Integer> queue;

    public p1(Object lock, PriorityQueue<Integer> queue) {
        this.lock = lock;
        this.queue = queue;
    }



    @Override
    public void run() {

        Random random = new Random();

        for (int i =1; i <= 100; i++) {
            synchronized (lock){
                if (!queue.isEmpty()){
                    try {
                        lock.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int t  = random.nextInt(190)+10;
                System.out.println(Thread.currentThread().getName() +" sleep = " + t+" add "+i);
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(queue.isEmpty()){
                    queue.offer(i);
                }

                lock.notifyAll();
            }
        }

    }
}
