package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class Quiz4 {
    public static void main(String[] args) {
        String s = new String("abc");
        System.out.println(new StringBuffer(s).reverse());
        /*
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

         */
    }
}


class ZeroEvenOdd {
    private int n;
    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);
    private volatile int x;
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

            zero.acquire();
            printNumber.accept(0);
            ++x;
            if(x%2==1){
                odd.release();
            }else{
                even.release();
            }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        even.acquire();
        printNumber.accept(x);
        zero.release();

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        odd.acquire();
        printNumber.accept(x);
        zero.release();

    }

}