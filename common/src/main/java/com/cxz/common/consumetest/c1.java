package com.cxz.common.consumetest;


import java.util.PriorityQueue;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/7/1 10:47
 */
public class c1 implements Runnable {
    private Object lock ;
    private PriorityQueue<Integer> queue;
    public c1(Object lock,PriorityQueue<Integer> queue) {
        this.lock = lock;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                Thread t = Thread.currentThread();
                if (queue.isEmpty() ){
                    try {
                        System.out.println(t.getName() +" wait  " );
                        lock.wait();
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //only consume %2 ==0
                if (!queue.isEmpty()&&queue.peek()%2!=0){
                    try {
                        System.out.println(t.getName() +" wait  " );
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Integer i = queue.poll();
                    System.out.println(t.getName() +" consume  " + i);
                }

                lock.notifyAll();
            }
        }
    }
}
