package com.bruceeckel.threading.example03;

/**
 * This is example from the Bruce Eckel "Thinking in Java" second edition (Russian version) book:
 * Chapter.14 "MultiThreading", sub-section "Inheritance from Thread", pp.648-649
 *
 *
 */
public class SimpleThread extends Thread {

    private int countDown = 5;

    private static int threadCount = 0;

    private final int threadNumber = ++threadCount;

    public SimpleThread() {
        System.out.println("Create Thread #" + threadNumber);
    }

    public void run() {
        while(true) {

            System.out.println("Thread " + threadNumber + " (" + countDown + ")");

            if(--countDown == 0)
                return;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("Execution has been interrupted");
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            new SimpleThread().start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Execution has been interrupted");
            }
        }

        System.out.println("All threads have been started");
    }

}
