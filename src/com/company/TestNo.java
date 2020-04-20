package com.company;


class MyThread1 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {

            System.out.println("before");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after");
        }
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            this.notifyAll();
        }

    }
}
public class TestNo {


    public static void main(String[] args) {

        Thread t1 = new Thread(new MyThread1());
        Thread t2 = new Thread(new MyThread2());
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }

}
