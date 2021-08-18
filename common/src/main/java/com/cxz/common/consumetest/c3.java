package com.cxz.common.consumetest;


import java.util.PriorityQueue;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/7/1 10:47
 */
public class c3 implements Runnable {
    private Object lock ;
    private PriorityQueue<Integer> queue;
    public c3(Object lock, PriorityQueue<Integer> queue) {
        this.lock = lock;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                Thread t = Thread.currentThread();
                if (queue.isEmpty()){
                    try {
                        System.out.println(t.getName() +" wait  " );
                        lock.wait();
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!queue.isEmpty()&&!(queue.peek()%3==0||queue.peek()%2==0)){
                    Integer i = queue.poll();
                    System.out.println(t.getName() +" consume  " + i);
                }
                else{
                    try {
                        System.out.println(t.getName() +" wait  " );
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                lock.notifyAll();
            }
        }
    }
}
