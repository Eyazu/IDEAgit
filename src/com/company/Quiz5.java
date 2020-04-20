package com.company;

import java.util.Vector;
import java.util.function.IntConsumer;

public class Quiz5 {
    public static void main(String[] args) {
        int n = 15;
        FizzBuzz fb = new FizzBuzz(n);

        for(int i = 1; i <= n; ++i) {
            new Thread(() -> {
                try {
                    fb.fizz(() -> System.out.println("fizz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    fb.buzz(() -> System.out.println("buzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    fb.fizzbuzz(() -> System.out.println("fizzbuzz"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    fb.number(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

    }
}