package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Random rdm = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        Integer[] nums = new Integer[10];
        for(int i = 0; i < 10; ++i) {
            // arrayList.add(rdm.nextInt());
            nums[i] = rdm.nextInt();
        }
        Arrays.sort(nums, (a, b) -> b - a);
        for(int i = 0; i < 10; ++i) {
            System.out.print(nums[i] + " ");
        }

    }
}


class FooBar {
    private int n;
    /*
    private boolean flag = true;

    private Integer lock = new Integer(0);
    private  CyclicBarrier cb = new CyclicBarrier(2);
    private CountDownLatch cdl = new CountDownLatch(1);

    */


    private BlockingQueue<Integer> fooQueue;
    private BlockingQueue<Integer> barQueue;
    public FooBar(int n) {
        this.n = n;
        fooQueue = new LinkedBlockingDeque<Integer>();
        barQueue = new LinkedBlockingDeque<Integer>();
        fooQueue.add(0);

    }
    String s;


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooQueue.take();
            printFoo.run();
            barQueue.put(i);
            // printFoo.run() outputs "foo". Do not change or remove this line.

        }
    }



    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barQueue.take();
            printBar.run();
            fooQueue.put(i);
            // printBar.run() outputs "bar". Do not change or remove this line.
        }


    }
}



/*
class FooBar {
    private int n;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    //模拟生产者消费者模式，用于判断共享变量是否为空
    private boolean isEmpty=true;

    public FooBar1(int n) {
        this.n = n;
    }

    //类似生产者offer数据
    public void foo(Runnable printFoo) throws InterruptedException {

        lock.lock();

        for (int i = 0; i < n; i++) {

            while(!isEmpty){
                condition.await();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            isEmpty=false;

            condition.signal();

        }
        lock.unlock();

    }

    //类似消费者take数据
    public void bar(Runnable printBar) throws InterruptedException {
        lock.lock();

        for (int i = 0; i < n; i++) {

            while(isEmpty){
                condition.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            isEmpty=true;

            condition.signal();

        }
        lock.unlock();

    }
}


class FooBar {
    private int n;
    private CountDownLatch a;
    private CyclicBarrier barrier;// 使用CyclicBarrier保证任务按组执行
    public FooBar(int n) {
        this.n = n;
        a = new CountDownLatch(1);
        barrier = new CyclicBarrier(2);// 保证每组内有两个任务
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        try {
            for (int i = 0; i < n; i++) {
                printFoo.run();
                a.countDown();// printFoo方法完成调用countDown
                barrier.await();// 等待printBar方法执行完成
            }
        } catch(Exception e) {}
    }

    public void bar(Runnable printBar) throws InterruptedException {

        try {
            for (int i = 0; i < n; i++) {
                a.await();// 等待printFoo方法先执行
                printBar.run();
                a = new CountDownLatch(1); // 保证下一次依旧等待printFoo方法先执行
                barrier.await();// 等待printFoo方法执行完成
            }
        } catch(Exception e) {}
    }
}

 */


